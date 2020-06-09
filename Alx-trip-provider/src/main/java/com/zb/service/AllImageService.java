package com.zb.service;

import com.zb.entity.AllImage;
import com.zb.mapper.AllImageMapper;
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
public class AllImageService {
    @Autowired(required = false)
    private AllImageMapper allImageMapper;

    @GetMapping(value = "/findHotelimage/{targetId}")
    public List<AllImage> findHotelimage(@PathVariable("targetId") Integer targetId){
        return  allImageMapper.findHotelimage(targetId);
    };

    @GetMapping(value = "/findRoomimage/{targetId}")
    public List<AllImage>findRoomimage(@PathVariable("targetId") Integer targetId){
        return allImageMapper.findRoomimage(targetId);
    };
}
