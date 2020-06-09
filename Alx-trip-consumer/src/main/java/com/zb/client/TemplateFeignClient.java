package com.zb.client;

import com.zb.entity.ItripHotelTempStore;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/3
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface TemplateFeignClient {

    @GetMapping(value = "/findOrderCount/{hotelid}/{roomid}")
    public int findOrderCount(@PathVariable("hotelid") Integer hotelid, @PathVariable("roomid")Integer roomid);

    @GetMapping(value = "/findOrderDetail/{roomid}")
    public List<ItripHotelTempStore> findOrderDetail(@PathVariable("roomid")Integer roomid);

    @GetMapping(value = "/updateOrder/{roomid}/{userId}")
    public int updateOrder(@PathVariable("roomid")Integer roomid,@PathVariable("userId")Integer userId);

}
