package com.zb.controller;

import com.zb.entity.Room;
import com.zb.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/27
 * @Version V1.0
 */
@RestController
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping(value = "/myfindRoomByHotelid")
    public List<Room> findRoomByHotelid(Integer hotelid, String[] choose, String bedtype, String paytype) {
        return roomService.findRoomByHotelid(hotelid, choose, bedtype, paytype);
    }


    //抢购方法
    @GetMapping(value = "/qg/{roomId}/{token}")
    public String qgRoom(@PathVariable("roomId") Integer roomId, @PathVariable("token")String token) {
        return roomService.qgRoom(roomId, token);
    }

    //轮询方法
    @GetMapping(value = "/qgwhile/{roomId}/{token}")
    public String qgwhile(@PathVariable("roomId")Integer roomId, @PathVariable("token")String token){
        return roomService.qgwhile(roomId, token);
    }

}
