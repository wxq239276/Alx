package com.zb.service.impl;

import com.zb.client.AllImageFeignClient;
import com.zb.client.AreaFeignClient;
import com.zb.client.RoomFeignClient;
import com.zb.entity.AllImage;
import com.zb.entity.Area;
import com.zb.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaFeignClient areaFeignClient;


    @Override
    public List<Area> findoverSeaCity() {
        return areaFeignClient.findoverSeaCity();
    }

    @Override
    public List<Area> findChinaHotCity() {
        return areaFeignClient.findChinaHotCity();
    }

    @Override
    public List<Area> findHotArea(String areaname) {
        return areaFeignClient.findHotArea(areaname);
    }
}
