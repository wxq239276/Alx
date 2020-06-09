package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@Data
public class Comment {
    private Integer id;
    private Integer hotelId;
    private Integer productId;
    private Integer orderId;
    private Integer productType;
    private String content;
    private Integer userId;
    private Integer isHavingImg;
    private Integer positionScore;
    private Integer facilitiesScore;
    private Integer serviceScore;
    private Integer hygieneScore;
    private Integer score;
    private Integer tripMode;
    private Integer isOk;

}
