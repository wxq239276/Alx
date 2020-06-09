package com.zb.entity;


public class ItripPurchaserecord {

  private String id;
  private String goodsId;
  private String userId;
  private String charge;
  private Double price;
  private String valid;
  private String startTime;
  private String endTime;
  private String status;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getGoodsId() {
    return goodsId;
  }

  public void setGoodsId(String goodsId) {
    this.goodsId = goodsId;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getCharge() {
    return charge;
  }

  public void setCharge(String charge) {
    this.charge = charge;
  }


  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }


  public String getValid() {
    return valid;
  }

  public void setValid(String valid) {
    this.valid = valid;
  }


  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }


  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
