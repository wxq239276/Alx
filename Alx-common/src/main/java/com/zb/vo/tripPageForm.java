package com.zb.vo;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@Data
public class tripPageForm {
    private Integer[] location;     //地域编号
    private Integer[] price;        //价位
    private Integer[] level;        //酒店星级
    private Integer[] feature;      //酒店特色
    private Integer start;          //当前页码
    private Integer size;           //每页显示几条数据
    private Integer[] paytype;        //选择支付类型
    private String ordertype;       //排序的方式

    public Integer[] getLocation() {
        return location;
    }

    public void setLocation(Integer[] location) {
        this.location = location;
    }

    public Integer[] getPrice() {
        return price;
    }

    public void setPrice(Integer[] price) {
        this.price = price;
    }

    public Integer[] getLevel() {
        return level;
    }

    public void setLevel(Integer[] level) {
        this.level = level;
    }

    public Integer[] getFeature() {
        return feature;
    }

    public void setFeature(Integer[] feature) {
        this.feature = feature;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer[] getPaytype() {
        return paytype;
    }

    public void setPaytype(Integer[] paytype) {
        this.paytype = paytype;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }
}
