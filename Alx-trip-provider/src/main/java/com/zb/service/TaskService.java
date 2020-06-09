package com.zb.service;

import com.zb.entity.ItripTask;
import com.zb.mapper.TaskMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/8
 * @Version V1.0
 */
@RestController
public class TaskService {
    @Autowired(required = false)
    private TaskMapper taskMapper;

    //向任务表中增加一条新的任务记录
    @PostMapping(value = "/insertTask")
    public int insertTask(@RequestBody ItripTask itripTask){
        return taskMapper.insertTask(itripTask);
    };

    //根据任务编号查询任务信息
    @GetMapping(value = "/findTaskByid/{id}")
    public ItripTask findTaskByid(@PathVariable("id")String id){
        return taskMapper.findTaskByid(id);
    };

    //为防止任务信息一致，每次发送的消息都应更改其发送的时间为当前时间
    @PostMapping(value = "/updateTasktime")
    public int updateTasktime(@RequestBody ItripTask itripTask){
        return taskMapper.updateTasktime(itripTask);
    };

    //查询一分钟之后的任务
    @GetMapping(value = "/findByUpdateTimeBefore")
    public List<ItripTask> findByUpdateTimeBefore(){
        return taskMapper.findByUpdateTimeBefore();
    };

    //乐观锁(当有线程准备修改数据时，修改这条记录的乐观锁版本，其它的线程拿这个版本与其本身的version对比，如果不一致，则不能进行修改)
    @PostMapping(value = "/updateTaskVersion")
    public int updateTaskVersion(@RequestBody  ItripTask itripTask){
        return taskMapper.updateTaskVersion(itripTask);
    };

    //删除任务（接收到record模块发送到MQ中的消息后）
    @GetMapping(value = "/delTask/{id}")
    public int delTask(@PathVariable("id")String id){
        return taskMapper.delTask(id);
    }

}
