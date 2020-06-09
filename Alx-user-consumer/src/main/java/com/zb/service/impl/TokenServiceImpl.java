package com.zb.service.impl;

import com.alibaba.fastjson.JSON;
import com.zb.entity.ItripUser;
import com.zb.service.TokenService;
import com.zb.util.MD5;
import com.zb.util.RedisUtil;
import nl.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisUtil redisUtil;


    //生成token
    @Override
    public String createToken(String agent, String userCode) {
         StringBuffer token = new StringBuffer("token-");
        UserAgent userAgent=UserAgent.parseUserAgentString(agent);
        if (userAgent.getOperatingSystem().isMobileDevice()) {
            token.append("MOBILE-");
        } else {
            token.append("PC-");
        }
        token.append(MD5.getMd5(userCode, 32) + "-");
        //token.append(userCode + "-");
        token.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "-");
        token.append(MD5.getMd5(agent, 6));
        return token.toString();
    }

    //将token-user键值对存到redis
    @Override
    public void saveToken(String token, ItripUser itripUser) {
        if (token.startsWith("token-MOBILE-")) {
            redisUtil.set(token, JSON.toJSONString(itripUser));
        } else {
            redisUtil.set(token, JSON.toJSONString(itripUser),60*60*2);
        }
    }
}
