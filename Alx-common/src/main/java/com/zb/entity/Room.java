package com.zb.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@Data
public class Room {
    private Integer id;
    private Integer hotelId;
    private String roomTitle;
    private Double roomPrice;
    private Integer roomBedTypeId;
    private Integer isHavingBreakfast;
    private Integer payType;
    private Double satisfaction;
    private Integer isBook;
    private Integer isCancel;
    private Integer isTimelyResponse;
    private Double minprice;
    private List<AllImage> images;  //酒店图片
    private String roomBedType;     //床型
    private Integer store;      //库存
    private Integer isdicount;  //是否是特价房（0：否  1：是）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public void setRoomTitle(String roomTitle) {
        this.roomTitle = roomTitle;
    }

    public Double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Integer getRoomBedTypeId() {
        return roomBedTypeId;
    }

    public void setRoomBedTypeId(Integer roomBedTypeId) {
        this.roomBedTypeId = roomBedTypeId;
    }

    public Integer getIsHavingBreakfast() {
        return isHavingBreakfast;
    }

    public void setIsHavingBreakfast(Integer isHavingBreakfast) {
        this.isHavingBreakfast = isHavingBreakfast;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getSatisfaction() {
        return satisfaction;
    }

    public void setSatisfaction(Double satisfaction) {
        this.satisfaction = satisfaction;
    }

    public Integer getIsBook() {
        return isBook;
    }

    public void setIsBook(Integer isBook) {
        this.isBook = isBook;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getIsTimelyResponse() {
        return isTimelyResponse;
    }

    public void setIsTimelyResponse(Integer isTimelyResponse) {
        this.isTimelyResponse = isTimelyResponse;
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

    public String getRoomBedType() {
        return roomBedType;
    }

    public void setRoomBedType(String roomBedType) {
        this.roomBedType = roomBedType;
    }

    public Integer getStore() {
        return store;
    }

    public void setStore(Integer store) {
        this.store = store;
    }

    public Integer getIsdicount() {
        return isdicount;
    }

    public void setIsdicount(Integer isdicount) {
        this.isdicount = isdicount;
    }
}
