package com.zb.service;

import com.alibaba.fastjson.JSON;
import com.zb.entity.ItripUser;
import com.zb.mapper.UserMapper;
import com.zb.util.RedisUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
@RestController
public class RestUserService {
    @Autowired(required = false)
    private UserMapper userMapper;
   @Autowired
    private RedisUtil redisUtil;


    //通过usercode及userpassword进行登陆，返回一个user对象
    @GetMapping(value = "/findUserLogin/{userCode}/{userPassword}")
    public List<ItripUser> findUserLogin(@PathVariable("userCode")String userCode, @PathVariable("userPassword")String userPassword){
        return userMapper.findUserLogin(userCode, userPassword);
    };

    //通过token从redis中查询user对象
    @GetMapping(value = "/getCurrentUser/{token}")
    public ItripUser getCurrentUser(@PathVariable("token") String token){
        String json = redisUtil.get(token).toString();
        if (json == null) {
            return null;
        }
        ItripUser user = JSON.parseObject(json, ItripUser.class);
        return user;
    }


}
