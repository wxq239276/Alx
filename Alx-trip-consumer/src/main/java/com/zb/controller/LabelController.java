package com.zb.controller;

import com.zb.entity.ItripLabelDic;
import com.zb.service.LabelService;
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
public class LabelController {
    @Autowired
    private LabelService labelService;

    @GetMapping(value = "myfindfeatureall")
    public List<ItripLabelDic> findfeatureall() {
        return labelService.findfeatureall();
    }

    @GetMapping(value = "/myfindBedtypeAll")
    public List<ItripLabelDic> findBedtypeAll() {
        return labelService.findBedtypeAll();
    }
}
