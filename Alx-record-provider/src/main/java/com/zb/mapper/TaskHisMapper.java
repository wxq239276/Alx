package com.zb.mapper;

import com.zb.entity.ItripTaskHis;
import org.apache.ibatis.annotations.Param;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
public interface TaskHisMapper {

    //向任务历史表中添加一条任务
    public int addTaskHis(ItripTaskHis itripTaskHis);

    //根据id查询历史任务
    public ItripTaskHis findTaskHis(@Param("id")String id);

    //修改历史任务
    public int updateHis(ItripTaskHis itripTaskHis);
}
