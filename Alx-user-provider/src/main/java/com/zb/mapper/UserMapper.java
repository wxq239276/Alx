package com.zb.mapper;

import com.zb.entity.ItripUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
public interface UserMapper {

    public List<ItripUser>findUserLogin(@Param("userCode")String userCode,@Param("userPassword")String userPassword);

}
