package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */

public class Feature {
    private Integer id;
    private Integer hotelId;
    private Integer featureId;
    private ItripLabelDic itripLabelDic;    //关联标签表(查酒店特色的名称)

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

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public ItripLabelDic getItripLabelDic() {
        return itripLabelDic;
    }

    public void setItripLabelDic(ItripLabelDic itripLabelDic) {
        this.itripLabelDic = itripLabelDic;
    }
}
