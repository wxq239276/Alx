package com.zb.service;

import com.zb.entity.ItripHotelOrder;
import com.zb.mapper.OrderMapper;
import javafx.scene.chart.ValueAxis;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/6
 * @Version V1.0
 */
@RestController
public class OrderService {
    @Autowired(required = false)
    private OrderMapper orderMapper;

    @PostMapping(value = "/insertOrder")
    public  int insertOrder(@RequestBody ItripHotelOrder itripHotelOrder){
        return orderMapper.insertOrder(itripHotelOrder);
    };

    @GetMapping(value = "/findOrderByid/{id}")
    public ItripHotelOrder findOrderByid(@PathVariable("id")Long id){
        return orderMapper.findOrderByid(id);
    };

    //修改订单的状态。交易编号改为支付宝产生的交易编号，（支付完成以后进行修改）
    @GetMapping(value = "/updateOrderstate/{orderNo}/{tradeNo}")
    public int updateOrderstate (@PathVariable("orderNo")String orderNo,@PathVariable("tradeNo")String tradeNo){
        return orderMapper.updateOrder(orderNo, tradeNo);
    };

    //定时检查订单状态为已支付，且入住日期在当前日期之前的订单，将其订单状态修改为已消费
    @GetMapping(value = "/updateStatetohasConsumed")
    public int updateStatetohasConsumed(){
        return orderMapper.updateStatetohasConsumed();
    };

    //根据用户id查出来最后添加一条该用户的订单信息
    @GetMapping(value = "/findOrderByUserid/{userId}")
    public ItripHotelOrder findOrderByUserid(@PathVariable("userId")Integer userId){
        return orderMapper.findOrderByUserid(userId);
    };

    //当用户下单30分钟后未支付，将订单状态改为已取消
    @GetMapping(value = "/updateStatetoFinish/{orderNo}")
    public int updateStatetoFinish(@PathVariable("orderNo")String orderNo){
        return orderMapper.updateStatetoFinish(orderNo);
    };

    //根据订单号查询订单
    @GetMapping(value = "/findOrderByOrderNo/{orderNo}")
    public ItripHotelOrder findOrderByOrderNo(@PathVariable("orderNo")String orderNo){
        return orderMapper.findOrderByOrderNo(orderNo);
    };
}
