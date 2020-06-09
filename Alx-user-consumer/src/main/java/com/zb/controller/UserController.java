package com.zb.controller;

import com.zb.entity.ItripUser;
import com.zb.service.TokenService;
import com.zb.service.UserService;
import com.zb.util.RedisUtil;
import com.zb.vo.UserTokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/5/4
 * @Version V1.0
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping(value = "/UserLogin")
    public UserTokenVo UserLogin(String userCode, String userPassword, HttpServletRequest request){
        ItripUser itripUser=userService.findUserLogin(userCode, userPassword);
        if (itripUser!=null){
            //生成token
            String token=tokenService.createToken(request.getHeader("user-agent"),itripUser.getUserCode());
            //把token-user键值对保存到redis
            tokenService.saveToken(token,itripUser);
            UserTokenVo userTokenVo=new UserTokenVo();
            userTokenVo.setToken(token);
            userTokenVo.setCurrTime(System.currentTimeMillis());
            userTokenVo.setExpTime(System.currentTimeMillis()+2*60*60*1000);
            return userTokenVo;
        }
        return null;
    }

    //向redis中添加50条用户信息
    @GetMapping(value = "/init")
    public int initUser(){
        for (int i = 2; i <= 50; i++) {
            String token="token-"+i;
            redisUtil.set(token,"{\n" +
                    "  \"activated\": 1,\n" +
                    "  \"flatID\":  12,\n" +
                    "  \"id\":  "+i+",\n" +
                    "  \"userCode\": \"1044732267@qq.com\",\n" +
                    "  \"userName\": \"tester\",\n" +
                    "  \"userPassword\": \"123456\",\n" +
                    "  \"userType\":  0\n" +
                    "}");
        }
        return 0;
    }

    @GetMapping(value = "/getCurrentUser/{token}")
    public ItripUser getCurrentUser(@PathVariable("token") String token){
        return userService.getCurrentUser(token);
    };
}
