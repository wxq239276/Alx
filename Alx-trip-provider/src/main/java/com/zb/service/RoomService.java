package com.zb.service;

import com.alibaba.fastjson.JSON;
import com.zb.entity.ItripHotelTempStore;
import com.zb.entity.Room;
import com.zb.mapper.RoomMapper;
import com.zb.mapper.TemplateStoreMapper;
import com.zb.util.IdWorker;
import com.zb.util.RedisUtil;
import com.zb.vo.RoomForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@RestController
public class RoomService {
    @Autowired(required = false)
    private RoomMapper roomMapper;
    @Autowired(required = false)
    private TemplateStoreMapper templateStoreMapper;
    @Autowired
    private RedisUtil redisUtil;

    //根据酒店id查询最低价
    @GetMapping(value = "/findMinprice/{hotelid}")
    public Double findMinprice(@PathVariable("hotelid")Integer hotelid){
        return roomMapper.findMinprice(hotelid);
    };

    //组合查询
    @PostMapping(value = "/findRoomByHotelid")
    public List<Room> findRoomByHotelid(@RequestBody  RoomForm roomForm){
        return roomMapper.findRoomByHotelid(roomForm);
    };

    //查询所有特价房间
    @GetMapping(value = "/findDiscountRoom")
    public List<Room>findDiscountRoom(){
        return roomMapper.findDiscountRoom();
    };

    //查询库存（根据roomid直接查redis）
    @GetMapping(value = "/checkRoomStroe/{roomId}")
    public int checkRoomStroe(@PathVariable("roomId")Integer roomId){
        String key= "discountRoom-" + roomId;
        if (redisUtil.hasKey(key)){
            String strjson=redisUtil.get(key).toString();
            Room room= JSON.parseObject(strjson,Room.class);
            if (room.getStore()>0){
                return 1;
            }
        }
        return 0;
    }

    //添加临时记录
    @GetMapping(value = "/LockRoomStore/{roomId}/{uid}")
    public int LockRoomStore(@PathVariable("roomId") Integer roomId ,@PathVariable("uid") Integer uid){
        //获取redis中的数据
        String key= "discountRoom-" + roomId;
        String strjson=redisUtil.get(key).toString();
        Room room= JSON.parseObject(strjson,Room.class);
        //创建临时库存记录
        ItripHotelTempStore itripHotelTempStore=new ItripHotelTempStore();
        itripHotelTempStore.setCreatedBy(uid);
        itripHotelTempStore.setHotelId(room.getHotelId());
        itripHotelTempStore.setId(Long.parseLong(IdWorker.getId()));
        itripHotelTempStore.setRoomId(roomId);
        itripHotelTempStore.setStatus(0);
        itripHotelTempStore.setStore(room.getStore());
        //修改库存
        room.setStore(room.getStore()-1);
        //只要调用了这个方法就给它重新写到redis中（已修改库存）
        redisUtil.set(key,JSON.toJSONString(room));
        //向数据库添加纪录
        return templateStoreMapper.insertHotelStore(itripHotelTempStore);
    }

}
