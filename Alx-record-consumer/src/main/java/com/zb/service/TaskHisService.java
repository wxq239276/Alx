package com.zb.service;

import com.zb.entity.ItripTaskHis;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
public interface TaskHisService {

    public int addTaskHis(ItripTaskHis itripTaskHis);

    public ItripTaskHis findTaskHis(String id);

    public int updateHis(ItripTaskHis itripTaskHis);

}
