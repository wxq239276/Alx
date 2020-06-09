package com.zb.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 范杰
 * @Description 酒店详情表
 * @Date 2020/4/21
 * @Version V1.0
 */
@Data
public class HotelDetail {
    private Integer id;
    private String hotelName;
    private Integer countryId;
    private  Integer provinceId;
    private Integer cityId;
    private String address;
    private String details;
    private String facilities;
    private String hotelPolicy;
    private Integer hotelType;
    private  Integer hotelLevel;
    private Integer isGroupPurchase;
    private String redundantCityName;
    private String redundantProvinceName;
    private String redundantCountryName;
    private Integer redundantHotelStore;
    private Double minprice;    //最低价
    private List<AllImage> images;  //酒店图片
    private Double wholeScore;  //酒店综合评分
    private Double recommend;   //酒店好评率
    private Integer commentnum; //酒店评论人数
    private List<Feature>  features;    //酒店特色

}
