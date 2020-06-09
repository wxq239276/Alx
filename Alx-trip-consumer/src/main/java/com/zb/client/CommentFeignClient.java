package com.zb.client;

import com.zb.entity.Comment;
import com.zb.entity.CommentScore;
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
public interface CommentFeignClient {

    @GetMapping(value = "/findScore/{hotelid}")
    public List<Comment> findScore(@PathVariable("hotelid")Integer hotelid);
}
