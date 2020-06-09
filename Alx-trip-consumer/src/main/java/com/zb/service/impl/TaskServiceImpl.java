package com.zb.service.impl;

import com.zb.client.TaskFeignClient;
import com.zb.client.TaskhisFeignClient;
import com.zb.entity.ItripTask;
import com.zb.entity.ItripTaskHis;
import com.zb.service.TaskService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/8
 * @Version V1.0
 */
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskFeignClient taskFeignClient;
    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private TaskhisFeignClient taskhisFeignClient;

    @Override
    //查询一分钟之前的任务
    public List<ItripTask> findByUpdateTimeBefore() {
        return taskFeignClient.findByUpdateTimeBefore();
    }

    //获取乐观锁
    @Override
    public int findTaskByidAndversion(String id, Integer version) {
        ItripTask itripTask=new ItripTask();
        itripTask.setId(id);
        itripTask.setVersion(version);
        //获取到乐观锁就返回1，未获取则返回0
        return taskFeignClient.updateTaskVersion(itripTask);
    }

    //发送消息到消息队列，并修改任务的更新时间(因为定时任务会一直向MQ发送消息)
    @Override
    public void publishTask(ItripTask itripTask) {
        //查询要发布的任务
        //ItripTask publishTask=taskFeignClient.findTaskByid(itripTask.getId());
        if (itripTask!=null){
            //发送消息到MQ
            amqpTemplate.convertAndSend(itripTask.getMqExchange(),itripTask.getMqRoutingkey(),itripTask);
            //修改任务的更新时间
            taskFeignClient.updateTasktime(itripTask);
        }
    }

    @Override
    public void finishTask(ItripTask itripTask) {
        ItripTaskHis myhis=taskhisFeignClient.getHisByid(itripTask.getId());
        if (myhis==null) {  //如果历史任务表中没有，就执行添加（幂等性判断）
            //向历史任务表中添加这条数据
            ItripTaskHis itripTaskHis = new ItripTaskHis();
            itripTaskHis.setId(itripTask.getId());
            itripTaskHis.setCreateTime(itripTask.getCreateTime());
            itripTaskHis.setDeleteTime(itripTask.getDeleteTime());
            itripTaskHis.setErrormsg(itripTask.getErrormsg());
            itripTaskHis.setMqExchange(itripTask.getMqExchange());
            itripTaskHis.setMqRoutingkey(itripTask.getMqRoutingkey());
            itripTaskHis.setRequestBody(itripTask.getRequestBody());
            itripTaskHis.setStatus(itripTask.getStatus());
            itripTaskHis.setTaskType(itripTask.getTaskType());
            itripTaskHis.setUpdateTime(itripTask.getUpdateTime());
            itripTaskHis.setVersion(itripTask.getVersion());
            taskhisFeignClient.insertHis(itripTaskHis);
        }else {     //如果有，就执行修改
            taskhisFeignClient.updateHis(myhis);
        }
        //从任务表中删除这条任务
        taskFeignClient.delTask(itripTask.getId());
    }
}
