package com.zb.service.impl;

import com.zb.client.TaskHisFeignClient;
import com.zb.entity.ItripTaskHis;
import com.zb.service.TaskHisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
@Service
public class TaskHisServiceImpl implements TaskHisService {
    @Autowired
    private TaskHisFeignClient taskHisFeignClient;

    @Override
    public int addTaskHis(ItripTaskHis itripTaskHis) {
        return taskHisFeignClient.addTaskHis(itripTaskHis);
    }

    @Override
    public ItripTaskHis findTaskHis(String id) {
        return taskHisFeignClient.findTaskHis(id);
    }

    @Override
    public int updateHis(ItripTaskHis itripTaskHis) {
        return taskHisFeignClient.updateHis(itripTaskHis);
    }
}
