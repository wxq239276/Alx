package com.zb.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 范杰
 * @Description 酒店详情表
 * @Date 2020/4/21
 * @Version V1.0
 */

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getHotelPolicy() {
        return hotelPolicy;
    }

    public void setHotelPolicy(String hotelPolicy) {
        this.hotelPolicy = hotelPolicy;
    }

    public Integer getHotelType() {
        return hotelType;
    }

    public void setHotelType(Integer hotelType) {
        this.hotelType = hotelType;
    }

    public Integer getHotelLevel() {
        return hotelLevel;
    }

    public void setHotelLevel(Integer hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public Integer getIsGroupPurchase() {
        return isGroupPurchase;
    }

    public void setIsGroupPurchase(Integer isGroupPurchase) {
        this.isGroupPurchase = isGroupPurchase;
    }

    public String getRedundantCityName() {
        return redundantCityName;
    }

    public void setRedundantCityName(String redundantCityName) {
        this.redundantCityName = redundantCityName;
    }

    public String getRedundantProvinceName() {
        return redundantProvinceName;
    }

    public void setRedundantProvinceName(String redundantProvinceName) {
        this.redundantProvinceName = redundantProvinceName;
    }

    public String getRedundantCountryName() {
        return redundantCountryName;
    }

    public void setRedundantCountryName(String redundantCountryName) {
        this.redundantCountryName = redundantCountryName;
    }

    public Integer getRedundantHotelStore() {
        return redundantHotelStore;
    }

    public void setRedundantHotelStore(Integer redundantHotelStore) {
        this.redundantHotelStore = redundantHotelStore;
    }

    public Double getMinprice() {
        return minprice;
    }

    public void setMinprice(Double minprice) {
        this.minprice = minprice;
    }

    public List<AllImage> getImages() {
        return images;
    }

    public void setImages(List<AllImage> images) {
        this.images = images;
    }

    public Double getWholeScore() {
        return wholeScore;
    }

    public void setWholeScore(Double wholeScore) {
        this.wholeScore = wholeScore;
    }

    public Double getRecommend() {
        return recommend;
    }

    public void setRecommend(Double recommend) {
        this.recommend = recommend;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }
}
