package com.zb.service;

import com.zb.entity.Room;
import com.zb.vo.RoomForm;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/27
 * @Version V1.0
 */
public interface RoomService {
    public List<Room> findRoomByHotelid(Integer hotelid,String[]choose,String bedtype,String paytype);

    public void roomStoreToRedis();

    //抢购方法
    public String qgRoom(Integer roomId,String token);

    //轮询方法
    public String qgwhile(Integer roomId,String token);
}
