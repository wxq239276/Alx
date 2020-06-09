package com.zb.service;

import com.zb.entity.ItripTaskHis;
import com.zb.mapper.TaskHisMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
@RestController
public class RestTaskHisService {
    @Autowired(required = false)
    private TaskHisMapper taskHisMapper;

    //向任务历史表中添加一条任务
    @PostMapping(value = "/addTaskHis")
    public int addTaskHis(@RequestBody  ItripTaskHis itripTaskHis){
        return taskHisMapper.addTaskHis(itripTaskHis);
    };

    @GetMapping(value = "/findTaskHis/{id}")
    public ItripTaskHis findTaskHis(@PathVariable("id")String id){
        return taskHisMapper.findTaskHis(id);
    };

    //修改历史任务
    @PostMapping(value = "/updateHis")
    public int updateHis(@RequestBody  ItripTaskHis itripTaskHis){
        return taskHisMapper.updateHis(itripTaskHis);
    };
}
