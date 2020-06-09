package com.zb.service;

import com.zb.entity.Area;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
public interface AreaService {

    public List<Area> findoverSeaCity();

    public List<Area>findChinaHotCity();

    public List<Area>findHotArea(String areaname);
}
