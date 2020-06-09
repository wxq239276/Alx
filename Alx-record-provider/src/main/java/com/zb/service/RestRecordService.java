package com.zb.service;

import com.zb.entity.ItripPurchaserecord;
import com.zb.mapper.RecordMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
@RestController
public class RestRecordService {
    @Autowired(required = false)
    private RecordMapper recordMapper;

    //根据用户id和商品id查询记录
    @GetMapping(value = "/findRecordByid/{userId}/{goodsId}")
    public ItripPurchaserecord findRecordByid(@PathVariable("userId")String userId, @PathVariable("goodsId")String goodsId){
        return recordMapper.findRecordByid(userId, goodsId);
    };

    //如果记录表中有这个商品的购买记录，就进行修改
    @PostMapping(value = "/updateRecord")
    public int updateRecord(@RequestBody  ItripPurchaserecord itripPurchaserecord){
        return recordMapper.updateRecord(itripPurchaserecord);
    };

    //如果记录表中没有购买记录，就进行添加
    @PostMapping(value = "/addRecord")
    public int addRecord(@RequestBody ItripPurchaserecord itripPurchaserecord){
        return recordMapper.addRecord(itripPurchaserecord);
    };

}
