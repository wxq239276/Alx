package com.zb.job;

import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.entity.ItripTask;
import com.zb.service.TaskService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/10
 * @Version V1.0
 */
@Component
public class TaskJob {
    @Autowired
    private TaskService taskService;

    @Scheduled(cron = "0/5 * * * * *")
    public void send(){
        //查询一分钟之前的数据
        List<ItripTask>taskList=taskService.findByUpdateTimeBefore();
        for(ItripTask t:taskList){
            System.out.println(t.getId()+"\t"+t.getRequestBody()+t.getUpdateTime());
            //当前线程获取锁的信息,防止多线程下， 同一个任务多次执行
            if(taskService.findTaskByidAndversion(t.getId(),t.getVersion())>0) {
                //调用定时发送操作
                taskService.publishTask(t);
            }
        }
    }

    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE)
    public void reviceTaskSuccess(ItripTask execTask , Message message , Channel channel){
        //删除任务， 历史存储
        taskService.finishTask(execTask);
    }

}
