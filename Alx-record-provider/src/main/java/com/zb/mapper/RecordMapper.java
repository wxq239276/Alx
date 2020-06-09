package com.zb.mapper;

import com.zb.entity.ItripPurchaserecord;
import org.apache.ibatis.annotations.Param;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
public interface RecordMapper {
    //根据用户id和商品id查询记录
    public ItripPurchaserecord findRecordByid(@Param("userId")String userId,@Param("goodsId")String goodsId);

    //如果记录表中有这个商品的购买记录，就进行修改
    public int updateRecord(ItripPurchaserecord itripPurchaserecord);

    //如果记录表中没有购买记录，就进行添加
    public int addRecord(ItripPurchaserecord itripPurchaserecord);

}
