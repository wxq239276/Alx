package com.zb.client;

import com.zb.entity.Feature;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface FeatureFeignClient {
    @GetMapping(value = "/findHotelFeature/{hotelid}")
    public List<Feature> findHotelFeature(@PathVariable("hotelid")Integer hotelid);
}
