package com.zb.vo;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/26
 * @Version V1.0
 */
@Data
public class RoomForm {
    private Integer hotelid;    //酒店id
    private String[] choose;    //含早餐、立即确认、免费取消、可预定
    private Integer paytypeid;     //支付类型
    private Integer bedtypeid;     //床型

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public String[] getChoose() {
        return choose;
    }

    public void setChoose(String[] choose) {
        this.choose = choose;
    }

    public Integer getPaytypeid() {
        return paytypeid;
    }

    public void setPaytypeid(Integer paytypeid) {
        this.paytypeid = paytypeid;
    }

    public Integer getBedtypeid() {
        return bedtypeid;
    }

    public void setBedtypeid(Integer bedtypeid) {
        this.bedtypeid = bedtypeid;
    }
}
