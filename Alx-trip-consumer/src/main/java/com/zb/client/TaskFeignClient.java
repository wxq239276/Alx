package com.zb.client;

import com.zb.entity.ItripTask;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/8
 * @Version V1.0
 */
@FeignClient(value = "trip-provider")
public interface TaskFeignClient {

    //向任务表中增加一条新的任务记录
    @PostMapping(value = "/insertTask")
    public int insertTask(@RequestBody ItripTask itripTask);

    //根据任务编号查询任务信息
    @GetMapping(value = "/findTaskByid/{id}")
    public ItripTask findTaskByid(@PathVariable("id")String id);

    //为防止任务信息一致，每次发送的消息都应更改其发送的时间为当前时间
    @PostMapping(value = "/updateTasktime")
    public int updateTasktime(@RequestBody ItripTask itripTask);

    //查询一分钟之后的任务
    @GetMapping(value = "/findByUpdateTimeBefore")
    public List<ItripTask> findByUpdateTimeBefore();

    //乐观锁(当有线程准备修改数据时，修改这条记录的乐观锁版本，其它的线程拿这个版本与其本身的version对比，如果不一致，则不能进行修改)
    @PostMapping(value = "/updateTaskVersion")
    public int updateTaskVersion(@RequestBody  ItripTask itripTask);

    //删除任务（接收到record模块发送到MQ中的消息后）
    @GetMapping(value = "/delTask/{id}")
    public int delTask(@PathVariable("id")String id);
}
