package com.zb.client;

import com.zb.entity.Area;
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
public interface AreaFeignClient {

    @GetMapping(value = "/findoverSeaCity")
    public List<Area> findoverSeaCity();

    @GetMapping(value = "/findChinaHotCity")
    public List<Area>findChinaHotCity();

    @GetMapping(value = "/findHotArea/{areaname}")
    public List<Area>findHotArea(@PathVariable("areaname")String areaname);
}
