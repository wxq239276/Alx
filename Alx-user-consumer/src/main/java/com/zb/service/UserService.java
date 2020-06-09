package com.zb.service;

import com.zb.entity.ItripUser;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
public interface UserService {

    public ItripUser findUserLogin(String userCode , String userPassword);

    public ItripUser getCurrentUser(String token);
}
