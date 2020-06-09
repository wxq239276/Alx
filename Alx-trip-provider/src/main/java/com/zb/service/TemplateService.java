package com.zb.service;

import com.zb.entity.ItripHotelTempStore;
import com.zb.mapper.TemplateStoreMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/3
 * @Version V1.0
 */
@RestController
public class TemplateService {
    @Autowired(required = false)
    private TemplateStoreMapper templateStoreMapper;

    @GetMapping(value = "/findOrderCount/{hotelid}/{roomid}")
    public int findOrderCount(@PathVariable("hotelid") Integer hotelid, @PathVariable("roomid")Integer roomid){
        return templateStoreMapper.findOrderCount(hotelid, roomid);
    };

    @GetMapping(value = "/findOrderDetail/{roomid}")
    public List<ItripHotelTempStore> findOrderDetail(@PathVariable("roomid")Integer roomid){
        return templateStoreMapper.findOrderDetail(roomid);
    };

    @GetMapping(value = "/updateOrder/{roomid}/{userId}")
    public int updateOrder(@PathVariable("roomid")Integer roomid,@PathVariable("userId")Integer userId){
        return templateStoreMapper.updateOrder(roomid, userId);
    };


}
