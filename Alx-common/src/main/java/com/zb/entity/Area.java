package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@Data
public class Area {
    private Integer id;
    private String name;
    private String areaNo;
    private Integer parent;
    private Integer isActivated;
    private Integer isTradingArea;
    private Integer isHot;
    private Integer level;
    private Integer isChina;
    private String pinyin;


}
