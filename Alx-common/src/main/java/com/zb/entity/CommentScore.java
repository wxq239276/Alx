package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */

public class CommentScore {
    private Integer hotelid;
    private Double positionScore;
    private Double facilitiesScore;
    private Double serviceScore;
    private Double hygieneScore;
    private Double score;
    private Integer commentnum;

    public Integer getHotelid() {
        return hotelid;
    }

    public void setHotelid(Integer hotelid) {
        this.hotelid = hotelid;
    }

    public Double getPositionScore() {
        return positionScore;
    }

    public void setPositionScore(Double positionScore) {
        this.positionScore = positionScore;
    }

    public Double getFacilitiesScore() {
        return facilitiesScore;
    }

    public void setFacilitiesScore(Double facilitiesScore) {
        this.facilitiesScore = facilitiesScore;
    }

    public Double getServiceScore() {
        return serviceScore;
    }

    public void setServiceScore(Double serviceScore) {
        this.serviceScore = serviceScore;
    }

    public Double getHygieneScore() {
        return hygieneScore;
    }

    public void setHygieneScore(Double hygieneScore) {
        this.hygieneScore = hygieneScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }
}
