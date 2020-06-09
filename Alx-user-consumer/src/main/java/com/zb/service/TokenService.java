package com.zb.service;

import com.zb.entity.ItripUser;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
public interface TokenService {

    public String createToken(String agent,String userCode);

    public void saveToken(String token, ItripUser itripUser);

}
