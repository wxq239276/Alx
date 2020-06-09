package com.zb.mapper;

import com.zb.entity.Room;
import com.zb.vo.RoomForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
public interface RoomMapper {

    //查询酒店最低价
    public Double findMinprice(@Param("hotelid")Integer hotelid);

    //查询某一个酒店下有哪些房型
    public List<Room>findRoomByHotelid(RoomForm roomForm);

    //查询所有特价房间
    public List<Room>findDiscountRoom();

}
