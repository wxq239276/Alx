package com.zb.client;

import com.zb.entity.ItripLabelDic;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface LabelFeignClient {

    @GetMapping(value = "/findfeatureall")
    public List<ItripLabelDic> findfeatureall();

    @GetMapping(value = "/findBedtypeAll")
    public List<ItripLabelDic>findBedtypeAll();
}
