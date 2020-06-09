package com.zb.mapper;

import com.zb.entity.HotelDetail;
import com.zb.vo.tripPageForm;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
public interface HotelDetailMapper {

    //根据对应的城市id查到这个城市的酒店
    public List<HotelDetail>findHotelByCityid(@Param("areaId") Integer areaId);

    //查询指定酒店id的详情
    public HotelDetail findHotelByid(@Param("id") Integer id);

    //查看所有酒店（用于存进redis）
    public List<HotelDetail>findHotelAll();

    //查询酒店（组合查询+分页显示）
    public List<HotelDetail>findHotelPage(tripPageForm tripPageForm);

    //分页查询酒店的条数
    public int findCount(tripPageForm tripPageForm);

}
