package com.zb.service.impl;

import com.zb.client.UserFeignClient;
import com.zb.entity.ItripUser;
import com.zb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public ItripUser findUserLogin(String userCode, String userPassword) {
        List<ItripUser>userList=userFeignClient.findUserLogin(userCode, userPassword);
        if(userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public ItripUser getCurrentUser(String token) {
        return userFeignClient.getCurrentUser(token);
    }
}
