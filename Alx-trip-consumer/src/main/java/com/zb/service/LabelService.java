package com.zb.service;

import com.zb.entity.ItripLabelDic;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
public interface LabelService {

    public List<ItripLabelDic> findfeatureall();

    public List<ItripLabelDic>findBedtypeAll();
}
