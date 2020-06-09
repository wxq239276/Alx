package com.zb.mq;

import com.rabbitmq.client.Channel;
import com.zb.config.RabbitMQConfig;
import com.zb.entity.ItripTask;
import com.zb.service.RecordService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
@Component
public class TaskMQ {
    @Autowired
    private RecordService recordService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RabbitListener(queues = RabbitMQConfig.XC_LEARNING_ADDCHOOSECOURSE)
    public void recevieTaskMQ(ItripTask execTask, Message message, Channel channel){
        System.out.println("记录模块接收商品模块发送的消息");
        System.out.println(execTask.getId()+"\t"+execTask.getRequestBody());
        boolean chooserecord=recordService.ChooseRecord(execTask);
        System.out.println(chooserecord);
        //添加成功的话就向另一个队列中发送消息，由trip模块接收
        if(chooserecord){
            amqpTemplate.convertAndSend(RabbitMQConfig.EX_LEARNING_ADDCHOOSECOURSE,RabbitMQConfig.XC_LEARNING_FINISHADDCHOOSECOURSE_KEY,execTask);
        }
    }

}
