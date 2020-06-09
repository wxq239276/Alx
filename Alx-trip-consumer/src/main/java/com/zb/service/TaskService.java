package com.zb.service;

import com.zb.entity.ItripTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/8
 * @Version V1.0
 */
public interface TaskService {

    //查询一分钟之后的任务
    public List<ItripTask> findByUpdateTimeBefore();

    //获取乐观锁
    public int findTaskByidAndversion(String id,Integer version);

    //发送消息到消息队列，并修改任务的更新时间
    public void publishTask(ItripTask itripTask);

    //删除任务（接收到record模块发送到MQ中的消息后）
    public void finishTask(ItripTask itripTask);
}
