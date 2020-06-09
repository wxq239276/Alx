package com.zb.service.impl;

import com.zb.client.LabelFeignClient;
import com.zb.entity.ItripLabelDic;
import com.zb.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@Service
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelFeignClient labelFeignClient;

    @Override
    public List<ItripLabelDic> findfeatureall() {
        return labelFeignClient.findfeatureall();
    }

    @Override
    public List<ItripLabelDic> findBedtypeAll() {
        return labelFeignClient.findBedtypeAll();
    }
}
