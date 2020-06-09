package com.zb.mapper;

import com.zb.entity.Area;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/22
 * @Version V1.0
 */
public interface AreaMapper {
    //显示5条海外城市
    public List<Area>findoverSeaCity();

    //显示5条国内热门城市
    public List<Area>findChinaHotCity();

    //根据用户选择的城市，动态显示5条热门区域
    public List<Area>findHotArea(@Param("areaname")String areaname);
}
