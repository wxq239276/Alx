package com.zb.service;

import com.zb.entity.ItripHotelOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/6
 * @Version V1.0
 */
public interface OrderService {

    public  int insertOrder( ItripHotelOrder itripHotelOrder);

    public ItripHotelOrder findOrderByid(Long id);

    public int updateOrderstate (String orderNo,String tradeNo);

    public void updateStatetohasConsumed();

    public ItripHotelOrder findOrderByUserid(Integer userId);
}
