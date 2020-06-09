package com.zb.mapper;

import com.zb.entity.AllImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
public interface AllImageMapper {

    //查找到酒店的图片地址
    public List<AllImage>findHotelimage(@Param("targetId") Integer targetId);

    //查询某个房间的图片
    public List<AllImage>findRoomimage(@Param("targetId") Integer targetId);
}
