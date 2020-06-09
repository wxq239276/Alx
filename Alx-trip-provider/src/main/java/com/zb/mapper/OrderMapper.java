package com.zb.mapper;

import com.zb.entity.ItripHotelOrder;
import org.apache.ibatis.annotations.Param;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/6
 * @Version V1.0
 */
public interface OrderMapper {

    //增加新的订单
    public  int insertOrder(ItripHotelOrder itripHotelOrder);

    //根据id查对应的订单信息
    public ItripHotelOrder findOrderByid(@Param("id")Long id);

    //修改订单的状态。交易编号改为支付宝产生的交易编号，（支付完成以后进行修改）
    public int updateOrder (@Param("orderNo")String orderNo,@Param("tradeNo")String tradeNo);

    //定时检查订单状态为已支付，且入住日期在当前日期之前的订单，将其订单状态修改为已消费
    public int updateStatetohasConsumed();

    //根据用户id查出来最后添加一条该用户的订单信息
    public ItripHotelOrder findOrderByUserid(@Param("userId")Integer userId);

    //当用户下单30分钟后未支付，将订单状态改为已取消
    public int updateStatetoFinish(@Param("orderNo")String orderNo);

    //根据订单号查询订单
    public ItripHotelOrder findOrderByOrderNo(@Param("orderNo")String orderNo);
}
