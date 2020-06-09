package com.zb.client;

import com.zb.entity.Room;
import com.zb.vo.RoomForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface RoomFeignClient {

    @GetMapping(value = "/findMinprice/{hotelid}")
    public Double findMinprice(@PathVariable("hotelid")Integer hotelid);

    @PostMapping(value = "/findRoomByHotelid")
    public List<Room> findRoomByHotelid(@RequestBody RoomForm roomForm);

    @GetMapping(value = "/findDiscountRoom")
    public List<Room>findDiscountRoom();


    //查询库存（根据roomid直接查redis）
    @GetMapping(value = "/checkRoomStroe/{roomId}")
    public int checkRoomStroe(@PathVariable("roomId")Integer roomId);

    //添加临时记录
    @GetMapping(value = "/LockRoomStore/{roomId}/{uid}")
    public int LockRoomStore(@PathVariable("roomId") Integer roomId ,@PathVariable("uid") Integer uid);

}
