package com.zb.controller;

import com.zb.entity.Area;
import com.zb.service.AreaService;
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
public class AreaController {
    @Autowired
    private AreaService areaService;

    @GetMapping("/myfindoverSeaCity")
    public List<Area> findoverSeaCity(){
        return areaService.findoverSeaCity();
    }

    @GetMapping("/myfindChinaHotCity")
    public List<Area> findChinaHotCity() {
        return areaService.findChinaHotCity();
    }

    @GetMapping(value = "/myfindHotArea/{areaname}")
    public List<Area>findHotArea(@PathVariable("areaname")String areaname){
        return areaService.findHotArea(areaname);
    };
}
