package com.zb.client;

import com.zb.entity.ItripPurchaserecord;
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
@FeignClient(value = "record-provider")
public interface RecordFeignClient {

    //根据用户id和商品id查询记录
    @GetMapping(value = "/findRecordByid/{userId}/{goodsId}")
    public ItripPurchaserecord findRecordByid(@PathVariable("userId")String userId, @PathVariable("goodsId")String goodsId);

    //如果记录表中有这个商品的购买记录，就进行修改
    @PostMapping(value = "/updateRecord")
    public int updateRecord(@RequestBody ItripPurchaserecord itripPurchaserecord);

    //如果记录表中没有购买记录，就进行添加
    @PostMapping(value = "/addRecord")
    public int addRecord(@RequestBody ItripPurchaserecord itripPurchaserecord);


}
