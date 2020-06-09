package com.zb.client;

import com.zb.entity.ItripTaskHis;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface TaskhisFeignClient {

    @PostMapping(value = "/insertHis")
    public int insertHis(@RequestBody ItripTaskHis itripTaskHis);

    //根据id查历史任务
    @GetMapping(value = "/getHisByid/{id}")
    public ItripTaskHis getHisByid(@PathVariable("id")String id);

    //修改历史任务
    @PostMapping(value = "/updateHis")
    public int updateHis(@RequestBody  ItripTaskHis itripTaskHis);
}
