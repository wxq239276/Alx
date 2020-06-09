package com.zb.service;

import com.zb.entity.HotelDetail;
import com.zb.mapper.HotelDetailMapper;
import com.zb.util.PageUtil;
import com.zb.vo.tripPageForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@RestController
public class HotelDetailService {
    @Autowired(required = false)
    private HotelDetailMapper hotelDetailMapper;

    @GetMapping(value = "/findHotelByCityid/{areaId}")
    public List<HotelDetail>findHotelByCityid(@PathVariable("areaId") Integer areaId){
        return hotelDetailMapper.findHotelByCityid(areaId);
    };

    @GetMapping(value = "/findHotelByid/{id}")
    public HotelDetail findHotelByid(@PathVariable("id") Integer id){
        return hotelDetailMapper.findHotelByid(id);
    };

    //查看所有酒店（用于存进redis）
    @GetMapping(value = "/findHotelAll")
    public List<HotelDetail>findHotelAll(){
        return hotelDetailMapper.findHotelAll();
    };

    @PostMapping(value = "/findHotelPage")
        public List<HotelDetail>findHotelPage(@RequestBody tripPageForm tripPageForm){
        /*PageUtil<HotelDetail>page=new PageUtil<>();
        List<HotelDetail>data=hotelDetailMapper.findHotelPage(tripPageForm);
        int count=hotelDetailMapper.findCount(tripPageForm);
        page.setData(data);
        page.setTotalNewscount(count);*/
        return hotelDetailMapper.findHotelPage(tripPageForm);
    };

    @PostMapping(value = "/findCount")
    public int findCount(@RequestBody tripPageForm tripPageForm){
        return hotelDetailMapper.findCount(tripPageForm);
    };
}
