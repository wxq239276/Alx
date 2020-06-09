package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.zb.client.OrderFeignClient;
import com.zb.client.TaskFeignClient;
import com.zb.config.DelayRabbitConfig;
import com.zb.entity.ItripHotelOrder;
import com.zb.entity.ItripTask;
import com.zb.service.OrderService;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtil;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/6
 * @Version V1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderFeignClient orderFeignClient;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private TaskFeignClient taskFeignClient;


    //消息发送者
    //用户下完单， 系统自动发起一个延迟队列，进行监督半小时之后订单状态一直未未支付，将次订单自动修改成已取消.
    @Override
    public int insertOrder(ItripHotelOrder itripHotelOrder) {
        int num= orderFeignClient.insertOrder(itripHotelOrder);
        System.out.println(itripHotelOrder.getOrderNo());
       if (num>0){
            //30分钟之后不支付，将这条订单消息发送给死信接收队列，由其进行修改状态处理
            //把消息封装发送到死信队列中，并设置其有效时间，有效期过后消息重新转发给死信接受队列，然后监听死信接收队列即可
            System.out.println("死信接收队列一分钟后接收消息");
            amqpTemplate.convertAndSend(DelayRabbitConfig.ORDER_DELAY_EXCHANGE, DelayRabbitConfig.ORDER_DELAY_ROUTING_KEY, itripHotelOrder, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setExpiration(20*1000+"");
                    return message;
                }
            });
            return num;
        }
        return num;

    }

    //消息接收者，监听死信接收队列
    @RabbitListener(queues = DelayRabbitConfig.ORDER_QUEUE_NAME)
    public void myreceiveDelMsg(ItripHotelOrder itripHotelOrder, Message message, Channel channel) {
        System.out.println("一分钟之后获取订单的状态");
        if (itripHotelOrder.getOrderStatus()==0){
            System.out.println("订单未支付，将此订单状态改为已取消");
            int num=orderFeignClient.updateStatetoFinish(itripHotelOrder.getOrderNo());
            if (num>0){
                System.out.println("此条订单已取消");
            }
        }
    }


    @Override
    public ItripHotelOrder findOrderByid(Long id) {
        return orderFeignClient.findOrderByid(id);
    }

    @Override
    public int updateOrderstate(String orderNo, String tradeNo) {
        ItripHotelOrder order=orderFeignClient.findOrderByOrderNo(orderNo);
        int num=orderFeignClient.updateOrderstate(orderNo, tradeNo);
        if (num>0){
            //根据订单的编号查询 用户和商品的信息
            ItripTask itripTask=new ItripTask();
            itripTask.setId(IdWorker.getId());
            itripTask.setMqExchange("ex_learning_addchoosecourse");
            itripTask.setMqRoutingkey("addchoosecourse");
            Map<String ,Object> requestBody = new HashMap<>();
            requestBody.put("user_Id",order.getUserId());
            requestBody.put("goods_Id",order.getRoomId());
            requestBody.put("charge","charge");
            requestBody.put("price",order.getPayAmount());
            requestBody.put("valid",order.getBookingDays()+"天");
            requestBody.put("start_time",order.getCheckInDate());
            requestBody.put("end_time",order.getCheckOutDate());
            requestBody.put("status",order.getOrderStatus());
            itripTask.setRequestBody(JSON.toJSONString(requestBody));
            itripTask.setStatus(1+"");
            itripTask.setTaskType(1+"");
            itripTask.setVersion(1);
            taskFeignClient.insertTask(itripTask);
        }
        return num;
    }

    //定时检查订单状态为已支付，且入住日期在当前日期之前的订单，将其订单状态修改为已消费
    @Scheduled(cron = "0 22 23 * * *")
    @Override
    public void updateStatetohasConsumed() {
        System.out.println("定时检查订单状态为已支付，且入住日期在当前日期之前的订单，将其订单状态修改为已消费");
        int num=orderFeignClient.updateStatetohasConsumed();
        if (num>0){
            System.out.println("有"+num+"条订单状态被修改");
        }
    }

    @Override
    public ItripHotelOrder findOrderByUserid(Integer userId) {
        return orderFeignClient.findOrderByUserid(userId);
    }
}
