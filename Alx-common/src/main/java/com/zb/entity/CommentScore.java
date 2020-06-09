package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@Data
public class CommentScore {
    private Integer hotelid;
    private Double positionScore;
    private Double facilitiesScore;
    private Double serviceScore;
    private Double hygieneScore;
    private Double score;
    private Integer commentnum;

}
