package com.zb.client;

import com.zb.entity.HotelDetail;
import com.zb.util.PageUtil;
import com.zb.vo.tripPageForm;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface HotelFeignClient {

    @GetMapping(value = "/findHotelByCityid/{areaId}")
    public List<HotelDetail>findHotelByCityid(@PathVariable("areaId") Integer areaId);

    @GetMapping(value = "/findHotelByid/{id}")
    public HotelDetail findHotelByid(@PathVariable("id") Integer id);

    @GetMapping(value = "/findHotelAll")
    public List<HotelDetail>findHotelAll();

    @PostMapping(value = "/findHotelPage")
    public List<HotelDetail> findHotelPage(@RequestBody tripPageForm tripPageForm);

    @PostMapping(value = "/findCount")
    public int findCount(@RequestBody tripPageForm tripPageForm);
}
