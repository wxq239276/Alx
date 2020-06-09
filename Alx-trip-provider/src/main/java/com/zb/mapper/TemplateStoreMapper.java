package com.zb.mapper;

import com.zb.entity.ItripHotelTempStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/3
 * @Version V1.0
 */
public interface TemplateStoreMapper {

    //根据酒店id和房间id查找在当前日期之后的预定记录数
    public int findOrderCount(@Param("hotelid") Integer hotelid,@Param("roomid")Integer roomid);

    //向临时库存表中添加一条预定记录
    public int insertHotelStore(ItripHotelTempStore itripHotelTempStore);

    //根据酒店id和房间id查找在当前日期之后的预定记录的清晰信息（主要看这条记录的status）
    public List<ItripHotelTempStore> findOrderDetail(@Param("roomid")Integer roomid);

    //如果记录状态为0就把这条记录作废，把status改为1
    public int updateOrder(@Param("roomid")Integer roomid,@Param("userId")Integer userId);
}
