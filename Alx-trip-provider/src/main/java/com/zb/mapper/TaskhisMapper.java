package com.zb.mapper;

import com.zb.entity.ItripTaskHis;
import org.apache.ibatis.annotations.Param;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
public interface TaskhisMapper {
    //增加历史任务
    public int insertHis(ItripTaskHis itripTaskHis);

    //根据id查历史任务
    public ItripTaskHis getHisByid(@Param("id")String id);

    //修改历史任务
    public int updateHis(ItripTaskHis itripTaskHis);
}
