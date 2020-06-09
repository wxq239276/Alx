package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.client.*;
import com.zb.entity.AllImage;
import com.zb.entity.Feature;
import com.zb.entity.HotelDetail;
import com.zb.service.HotelDetailService;
import com.zb.util.PageUtil;
import com.zb.vo.tripPageForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@Service
public class HotelDetailServiceImpl implements HotelDetailService {
    @Autowired
    private HotelFeignClient hotelFeignClient;
    @Autowired
    private RoomFeignClient roomFeignClient;
    @Autowired
    private AllImageFeignClient allImageFeignClient;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private CommentFeignClient commentFeignClient;
    @Autowired
    private FeatureFeignClient featureFeignClient;


    @Override
    public List<HotelDetail> findHotelByCityid(Integer areaId) {
       List<HotelDetail>list=hotelFeignClient.findHotelByCityid(areaId);
       for (HotelDetail h:list){
           List<AllImage>images=allImageFeignClient.findHotelimage(h.getId());
           Double minPrice=roomFeignClient.findMinprice(h.getId());
           h.setImages(images);
           h.setMinprice(minPrice);
       }
       return list;
    }

    @Override
    public HotelDetail findHotelByid(Integer id) {
        HotelDetail hotelDetail=hotelFeignClient.findHotelByid(id);
        List<Feature>features=featureFeignClient.findHotelFeature(hotelDetail.getId());
        hotelDetail.setFeatures(features);
        return hotelDetail;
    }

    //接收前台的数据，在这里拼装成前台需要显示数据的对象
    @Override
    public PageUtil<HotelDetail> findHotelPage(Integer[] location, Integer[] price, Integer[] level, Integer[] feature,
                                               Integer[] paytype, Integer index, Integer size,String ordertype) {
        //将查询条件封装成一个对象
        tripPageForm pageForm=new tripPageForm();
        pageForm.setStart((index-1)*size);
        pageForm.setSize(size);
        pageForm.setFeature(feature);
        pageForm.setLevel(level);
        pageForm.setLocation(location);
        pageForm.setPaytype(paytype);
        pageForm.setPrice(price);
        pageForm.setOrdertype(ordertype);
        //查询分页数据
        PageUtil<HotelDetail>page=new PageUtil<>();
        List<HotelDetail>data=hotelFeignClient.findHotelPage(pageForm);
        int count=hotelFeignClient.findCount(pageForm);
        page.setPageindex(index);
        page.setPagesize(size);
        page.setTotalNewscount(count);
        //将酒店的特色、图片属性set进去
        for (HotelDetail h:data){
            List<AllImage>images=allImageFeignClient.findHotelimage(h.getId());
            List<Feature>features=featureFeignClient.findHotelFeature(h.getId());
            h.setImages(images);
            h.setFeatures(features);
        }
        page.setData(data);
        return page;
    }
}
