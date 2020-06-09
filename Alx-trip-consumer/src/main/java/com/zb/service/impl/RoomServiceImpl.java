package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.client.AllImageFeignClient;
import com.zb.client.RoomFeignClient;
import com.zb.client.TemplateFeignClient;
import com.zb.client.UserFeignClient;
import com.zb.config.DelayRabbitConfig;
import com.zb.config.MQconfig;
import com.zb.entity.AllImage;
import com.zb.entity.ItripHotelTempStore;
import com.zb.entity.ItripUser;
import com.zb.entity.Room;
import com.zb.service.RoomService;
import com.zb.util.RedisUtil;
import com.zb.vo.RoomForm;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/27
 * @Version V1.0
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomFeignClient roomFeignClient;
    @Autowired
    private AllImageFeignClient allImageFeignClient;
    @Autowired
    private TemplateFeignClient templateFeignClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserFeignClient userFeignClient;
    @Autowired
    private AmqpTemplate amqpTemplate;


    //组合查询某个酒店有哪些房型，封装了房间图片的属性
    @Override
    public List<Room> findRoomByHotelid(Integer hotelid, String[] choose, String bedtype, String paytype) {
        RoomForm roomForm=new RoomForm();
        Integer bedtypeid=null;
        if (bedtype.equals("大床")){
            bedtypeid=2;
        }else if (bedtype.equals("双床")){
            bedtypeid=3;
        }else if (bedtype.equals("多床")){
            bedtypeid=4;
        }else if (bedtype.equals("单人床")){
            bedtypeid=5;
        }
        Integer paytypeid=null;
        if (paytype.equals("到店付")){
            paytypeid=2;
        }else if (paytype.equals("在线付")){
            paytypeid=1;
        }else if (paytype.equals("不限")){
            paytypeid=3;
        }
        roomForm.setHotelid(hotelid);
        roomForm.setChoose(choose);
        roomForm.setBedtypeid(bedtypeid);
        roomForm.setPaytypeid(paytypeid);
        List<Room>roomList=roomFeignClient.findRoomByHotelid(roomForm);
        //将房间的图片属性封装进去
        for (Room r:roomList){
            List<AllImage>imageList=allImageFeignClient.findRoomimage(r.getId());
            r.setImages(imageList);
        }
        return roomList;
    }

    //每天中午12点自动重置酒店房间的库存
    @Scheduled(cron = "0 07 20 * * *")
    @Override
    public void roomStoreToRedis() {
        System.out.println("自动刷新");
        //存储到redis中的房间集合
        List<Room> toRedisRoom = new ArrayList<>();
        //获取所有特价房间
        List<Room>roomList=roomFeignClient.findDiscountRoom();
        for(Room r:roomList){
            int storecount=templateFeignClient.findOrderCount(r.getHotelId(),r.getId());
            r.setStore(r.getStore()-storecount);
            toRedisRoom.add(r);
            //写入到redis中
            redisTemplate.opsForValue().set("discountRoom-"+r.getId(), JSON.toJSONString(r));
        }
    }

    //分布式锁
    // MQ用于解决高并发场景下使用锁产生的请求超时问题
    //消息发送者
    @Override
    public  String  qgRoom(Integer roomId, String token) {
        Map<String,Object>param=new HashMap<>();
        param.put("roomId",roomId);
        param.put("token",token);
        amqpTemplate.convertAndSend(MQconfig.TRIPEXCHANGE,"inform.trip",param);
        return "正在排队中.....";
    }

   //消息接收者
    @RabbitListener(queues = MQconfig.TRIPQUEUE)
    public void receiveMQ(Map<String,Object>param, Message message, Channel channel) {
        Integer roomId=Integer.parseInt(param.get("roomId").toString());
        String token=param.get("token").toString();
        System.out.println("房间id："+roomId+"token："+token);
        //获取用户的信息
        ItripUser itripUser=userFeignClient.getCurrentUser(token);
        //使用自定义的分布式锁，完成高并发场景下的抢购
        String key="lock-"+roomId;
        //用户未登录，返回0
        if (itripUser==null){
            System.out.println("用户未登陆，不能预定房间！！！");
            return ;
        }
        try {
            //如果redis中没有对应的锁，则进入循环等待，线程休眠
            //自旋
            while (!redisUtil.lock(key)){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int roomstore=roomFeignClient.checkRoomStroe(roomId);
            if (roomstore<=0){
                System.out.println("房间库存不足！！！");
                return ;
            }
            //向redis中添加那个用户的那个抢购房屋信息
            String qgKey = "qg-" + itripUser.getId() + "-" + roomId;
            //用户是否是二次预定
            if (redisUtil.hasKey(qgKey)){
                System.out.println("同一用户不能重复预定房间！！！");
                return;
            }

            int insertnum=roomFeignClient.LockRoomStore(roomId,itripUser.getId());
            if (insertnum>0){
                //1分钟之后不支付， 自动清除记录
                redisUtil.lSet(qgKey, JSON.toJSONString(itripUser), 60 * 1);
                System.out.println("预定成功，向db中添加一条记录！！！");

                //把消息封装发送到死信队列中，并设置其有效时间，有效期过后消息重新转发给死信接受队列，然后监听死信接收队列即可
                Map<String,Object>myparam=new HashMap<>();
                myparam.put("roomId",roomId);
                myparam.put("token",token);
                myparam.put("userId",itripUser.getId());
                System.out.println("死信接收队列一分钟后接收消息");
                amqpTemplate.convertAndSend(DelayRabbitConfig.TRIP_DELAY_EXCHANGE, DelayRabbitConfig.TRIP_DELAY_ROUTING_KEY, myparam, new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        message.getMessageProperties().setExpiration(60*1000+"");
                        return message;
                    }
                });
                return ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            redisUtil.unlock(key);
        }
    }

    //轮询的方方法
    @Override
    public String qgwhile(Integer roomId, String token) {
        //获取用户的信息
        ItripUser itripUser=userFeignClient.getCurrentUser(token);
        //查询库存
        int roomstore=roomFeignClient.checkRoomStroe(roomId);
        //查redis中是否有对应的key
        String qgKey = "qg-" + itripUser.getId() + "-" + roomId;
        //redis有对应的key，返回抢购成功
        if (redisUtil.hasKey(qgKey)){
            return "success";
        }else {
            //抢购失败
            if (roomstore<=0){
                return "input";
            }
            //正在排队中
            return "none";
        }
    }

    //监听死信接收队列
    @RabbitListener(queues = DelayRabbitConfig.TRIP_QUEUE_NAME)
    public void receiveDelMsg(Map<String,Object>param,Message message,Channel channel){
        System.out.println("一分钟之后获取到临时库存的状态信息");
        //获取map中的数据
        Integer roomId =Integer.parseInt(param.get("roomId").toString()) ;
        Integer userId = Integer.parseInt(param.get("userId").toString());
        //查预定记录的状态
        List<ItripHotelTempStore>statusList=templateFeignClient.findOrderDetail(roomId);

        for (ItripHotelTempStore st:statusList){
            if (st.getStatus()==0){
                System.out.println("修改临时库存的状态为1");
                int num=templateFeignClient.updateOrder(roomId,userId);
                if (num>0) {
                    System.out.println("回滚库存......");
                    String key="discountRoom-"+roomId;
                    String strjson=redisUtil.get(key).toString();
                    Room room=JSON.parseObject(strjson,Room.class);
                    //此条预定记录未下单，标记为作废记录，redis中的库存回滚
                    room.setStore(room.getStore()+1);
                    //重新存到redis中
                    redisUtil.set(key,JSON.toJSONString(room));
                }
            }
        }
           System.out.println("roomId:" + roomId + "\t" + "userId:" + userId);
    }

}
