package com.zb.entity;

import lombok.Data;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@Data
public class AllImage {
    private Integer id;
    private Integer type;
    private Integer targetId;
    private Integer position;
    private String imgUrl;

}
