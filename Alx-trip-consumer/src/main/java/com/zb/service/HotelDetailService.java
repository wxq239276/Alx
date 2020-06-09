package com.zb.service;

import com.zb.entity.HotelDetail;
import com.zb.util.PageUtil;
import com.zb.vo.tripPageForm;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
public interface HotelDetailService {

    public List<HotelDetail>findHotelByCityid(Integer areaId);

    public HotelDetail findHotelByid( Integer id);

    public PageUtil<HotelDetail> findHotelPage( Integer[] location,Integer[] price,
                                                Integer[] level,Integer[] feature,Integer[] paytype,
                                                Integer index,Integer size,String ordertype
                                                );

}
