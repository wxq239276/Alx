package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@Data
public class Feature {
    private Integer id;
    private Integer hotelId;
    private Integer featureId;
    private ItripLabelDic itripLabelDic;    //关联标签表(查酒店特色的名称)
}
