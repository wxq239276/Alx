package com.zb.entity;


public class ItripUser {

  private Integer id;
  private String userCode;
  private String userPassword;
  private Integer userType;
  private Integer flatId;
  private String userName;
  private String weChat;
  private String qq;
  private String weibo;
  private String baidu;
  private Integer activated;
  private Long modifiedBy;

  public Long getModifiedBy() {
    return modifiedBy;
  }

  public void setModifiedBy(Long modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }


  public Integer getFlatId() {
    return flatId;
  }

  public void setFlatId(Integer flatId) {
    this.flatId = flatId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getWeChat() {
    return weChat;
  }

  public void setWeChat(String weChat) {
    this.weChat = weChat;
  }


  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }


  public String getWeibo() {
    return weibo;
  }

  public void setWeibo(String weibo) {
    this.weibo = weibo;
  }


  public String getBaidu() {
    return baidu;
  }

  public void setBaidu(String baidu) {
    this.baidu = baidu;
  }


  public Integer getActivated() {
    return activated;
  }

  public void setActivated(Integer activated) {
    this.activated = activated;
  }

}
