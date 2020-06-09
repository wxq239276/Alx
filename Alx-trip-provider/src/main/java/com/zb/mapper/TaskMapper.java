package com.zb.mapper;

import com.zb.entity.ItripTask;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/8
 * @Version V1.0
 */
public interface TaskMapper {

    //向任务表中增加一条新的任务记录
    public int insertTask(ItripTask itripTask);

    //根据任务编号查询任务信息
    public ItripTask findTaskByid(@Param("id")String id);

    //为防止任务信息一致，每次发送的消息都应更改其发送的时间为当前时间
    public int updateTasktime(ItripTask itripTask);

    //查询一分钟之后的任务
    public List<ItripTask> findByUpdateTimeBefore();

    //乐观锁(当有线程准备修改数据时，修改这条记录的乐观锁版本，其它的线程拿这个版本与其本身的version对比，如果不一致，则不能进行修改)
    public int updateTaskVersion(ItripTask itripTask);

    //删除任务（接收到record模块发送到MQ中的消息后）
    public int delTask(@Param("id")String id);

}
