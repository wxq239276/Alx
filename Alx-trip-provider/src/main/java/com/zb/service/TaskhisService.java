package com.zb.service;

import com.zb.entity.ItripTaskHis;
import com.zb.mapper.TaskhisMapper;
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
public class TaskhisService {
  @Autowired(required = false)
    private TaskhisMapper taskhisMapper;

    @PostMapping(value = "/insertHis")
    public int insertHis(@RequestBody ItripTaskHis itripTaskHis){
        return taskhisMapper.insertHis(itripTaskHis);
    };

  //根据id查历史任务
  @GetMapping(value = "/getHisByid/{id}")
  public ItripTaskHis getHisByid(@PathVariable("id")String id){
    return taskhisMapper.getHisByid(id);
  };

  //修改历史任务
  @PostMapping(value = "/updateHis")
  public int updateHis(@RequestBody  ItripTaskHis itripTaskHis){
    return taskhisMapper.updateHis(itripTaskHis);
  };
}
