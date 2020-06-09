package com.zb.controller;

import com.zb.entity.HotelDetail;
import com.zb.service.HotelDetailService;
import com.zb.util.PageUtil;
import com.zb.vo.tripPageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@RestController
public class HotelDetailController {
    @Autowired
    private HotelDetailService hotelDetailService;

    //根据区域id查找对应区域下的酒店
    @GetMapping(value = "/myfindHotelByCityid/{areaId}")
    public List<HotelDetail> findHotelByCityid(@PathVariable("areaId") Integer areaId) {
        return hotelDetailService.findHotelByCityid(areaId);
    }

    //根据酒店id查找对应的酒店(封装了酒店特色属性)
    @GetMapping(value = "/myfindHotelByid/{id}")
    public HotelDetail findHotelByid(@PathVariable("id") Integer id) {
        return hotelDetailService.findHotelByid(id);
    }

    //分页+组查（封装了图片、特色）
    @PostMapping(value = "/myfindHotelPage")
    public PageUtil<HotelDetail> findHotelPage(Integer[] location,Integer[] price,
                                               Integer[] level,Integer[] feature,Integer[] paytype,
                                               Integer index,Integer size,String ordertype) {
        return hotelDetailService.findHotelPage(location, price, level, feature, paytype, index, size,ordertype);
    }


}
