package com.zb.mapper;

import com.zb.entity.Feature;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
public interface FeatureMapper {
    //查询酒店的特色
    public List<Feature>findHotelFeature(@Param("hotelid")Integer hotelid);
}
