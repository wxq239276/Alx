package com.zb.controller;

import com.zb.entity.ItripHotelOrder;
import com.zb.service.OrderService;
import com.zb.util.IdWorker;
import com.zb.util.OrderCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/6
 * @Version V1.0
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/myinsertOrder")
    public int myinsertOrder( ItripHotelOrder itripHotelOrder) {
        itripHotelOrder.setId(Long.parseLong(IdWorker.getId()));
        itripHotelOrder.setOrderNo(OrderCode.getOrderCode());
        return orderService.insertOrder(itripHotelOrder);
    }

    @GetMapping(value = "/myfindOrderByid/{id}")
    public ItripHotelOrder myfindOrderByid(@PathVariable("id")Long id){
        return orderService.findOrderByid(id);
    }

    //修改订单的状态。交易编号改为支付宝产生的交易编号，（支付完成以后进行修改）
    @GetMapping(value = "/myupdateOrderstate/{orderNo}/{tradeNo}")
    public int myupdateOrderstate (@PathVariable("orderNo")String orderNo,@PathVariable("tradeNo")String tradeNo){
        return orderService.updateOrderstate(orderNo, tradeNo);
    };

    @GetMapping(value = "/myfindOrderByUserid/{userId}")
    public ItripHotelOrder myfindOrderByUserid(@PathVariable("userId")Integer userId){
        return orderService.findOrderByUserid(userId);
    }

}
