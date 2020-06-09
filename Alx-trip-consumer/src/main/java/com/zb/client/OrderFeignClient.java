package com.zb.client;

import com.zb.entity.ItripHotelOrder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/6
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface OrderFeignClient {

    @PostMapping(value = "/insertOrder")
    public  int insertOrder(@RequestBody ItripHotelOrder itripHotelOrder);

    @GetMapping(value = "/findOrderByid/{id}")
    public ItripHotelOrder findOrderByid(@PathVariable("id")Long id);

    //修改订单的状态。交易编号改为支付宝产生的交易编号，（支付完成以后进行修改）
    @GetMapping(value = "/updateOrderstate/{orderNo}/{tradeNo}")
    public int updateOrderstate (@PathVariable("orderNo")String orderNo,@PathVariable("tradeNo")String tradeNo);

    //定时检查订单状态为已支付，且入住日期在当前日期之前的订单，将其订单状态修改为已消费
    @GetMapping(value = "/updateStatetohasConsumed")
    public int updateStatetohasConsumed();

    @GetMapping(value = "/findOrderByUserid/{userId}")
    public ItripHotelOrder findOrderByUserid(@PathVariable("userId")Integer userId);

    //当用户下单30分钟后未支付，将订单状态改为已取消
    @GetMapping(value = "/updateStatetoFinish/{orderNo}")
    public int updateStatetoFinish(@PathVariable("orderNo")String orderNo);

    //根据订单号查询订单
    @GetMapping(value = "/findOrderByOrderNo/{orderNo}")
    public ItripHotelOrder findOrderByOrderNo(@PathVariable("orderNo")String orderNo);
}
