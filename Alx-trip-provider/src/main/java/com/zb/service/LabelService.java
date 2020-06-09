package com.zb.service;

import com.zb.entity.ItripLabelDic;
import com.zb.mapper.LabelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/23
 * @Version V1.0
 */
@RestController
public class LabelService {
    @Autowired(required = false)
    private LabelMapper labelMapper;

    @GetMapping(value = "/findfeatureall")
    public List<ItripLabelDic> findfeatureall(){
        return labelMapper.findfeatureall();
    };

    @GetMapping(value = "/findBedtypeAll")
    public List<ItripLabelDic>findBedtypeAll(){
        return labelMapper.findBedtypeAll();
    };
}
