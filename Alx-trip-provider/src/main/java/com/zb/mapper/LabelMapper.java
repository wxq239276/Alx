package com.zb.mapper;

import com.zb.entity.ItripLabelDic;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
public interface LabelMapper {
    //查询所有的酒店特色
    public List<ItripLabelDic>findfeatureall();

    //查询所有的床型
    public List<ItripLabelDic>findBedtypeAll();
}
