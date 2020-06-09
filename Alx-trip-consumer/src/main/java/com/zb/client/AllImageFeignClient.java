package com.zb.client;

import com.zb.entity.AllImage;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface AllImageFeignClient {

    @GetMapping(value = "/findHotelimage/{targetId}")
    public List<AllImage> findHotelimage(@PathVariable("targetId") Integer targetId);

    @GetMapping(value = "/findRoomimage/{targetId}")
    public List<AllImage>findRoomimage(@PathVariable("targetId") Integer targetId);
}
