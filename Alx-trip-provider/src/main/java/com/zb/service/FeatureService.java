package com.zb.service;

import com.zb.entity.Feature;
import com.zb.mapper.FeatureMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@RestController
public class FeatureService {
    @Autowired(required = false)
    private FeatureMapper featureMapper;

    @GetMapping(value = "/findHotelFeature/{hotelid}")
    public List<Feature> findHotelFeature(@PathVariable("hotelid")Integer hotelid){
        return featureMapper.findHotelFeature(hotelid);
    };
}
