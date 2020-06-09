package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.client.RecordFeignClient;
import com.zb.client.TaskHisFeignClient;
import com.zb.entity.ItripPurchaserecord;
import com.zb.entity.ItripTask;
import com.zb.entity.ItripTaskHis;
import com.zb.service.RecordService;
import com.zb.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/11
 * @Version V1.0
 */
@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordFeignClient recordFeignClient;
    @Autowired
    private TaskHisFeignClient taskHisFeignClient;


    //根据MQ中传过来的任务对象，查询记录表中是否对应的记录，如果有，就修改，反之就增加
    @Override
    public boolean ChooseRecord(ItripTask itripTask) {
        try {
            //酒店微服务传过来的
            ItripPurchaserecord itripPurchaserecord= JSON.parseObject(itripTask.getRequestBody(),ItripPurchaserecord.class);
            //查询记录表中是否有购买记录
            ItripPurchaserecord myrecord=recordFeignClient.findRecordByid(itripPurchaserecord.getUserId(),itripPurchaserecord.getGoodsId());
            //之前没有记录，进行添加
            if (myrecord==null){
                itripPurchaserecord.setId(IdWorker.getId());
                recordFeignClient.addRecord(itripPurchaserecord);
            }else { //如果有就进行修改（幂等性）
                itripPurchaserecord.setStatus("1");
                recordFeignClient.updateRecord(itripPurchaserecord);
            }
            //将这个任务添加到itrip_purchaserecord_db`库的itrip_task_his表中，如果存在，则修改，反之则添加
            ItripTaskHis myhis=taskHisFeignClient.findTaskHis(itripTask.getId());
            if (myhis==null) {
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
                taskHisFeignClient.addTaskHis(itripTaskHis);
            }else {
                int num=taskHisFeignClient.updateHis(myhis);
                System.out.println(num);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
