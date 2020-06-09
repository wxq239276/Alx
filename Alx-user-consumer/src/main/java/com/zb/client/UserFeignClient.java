package com.zb.client;

import com.zb.entity.ItripUser;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
@FeignClient(value = "user-provider")
public interface UserFeignClient {

    @GetMapping(value = "/findUserLogin/{userCode}/{userPassword}")
    public List<ItripUser> findUserLogin(@PathVariable("userCode")String userCode, @PathVariable("userPassword")String userPassword);

    @GetMapping(value = "/getCurrentUser/{token}")
    public ItripUser getCurrentUser(@PathVariable("token") String token);
}
