package com.zb.service;

import com.zb.entity.Area;
import com.zb.mapper.AreaMapper;
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
public class AreaService {
    @Autowired(required = false)
    private AreaMapper areaMapper;

    @GetMapping(value = "/findoverSeaCity")
    public List<Area> findoverSeaCity(){
        return areaMapper.findoverSeaCity();
    };

    @GetMapping(value = "/findChinaHotCity")
    public List<Area>findChinaHotCity(){
        return areaMapper.findChinaHotCity();
    };

    @GetMapping(value = "/findHotArea/{areaname}")
    public List<Area>findHotArea(@PathVariable("areaname")String areaname){
        return areaMapper.findHotArea(areaname);
    };

}
