package com.zb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@SpringBootApplication
@EnableEurekaServer
public class MyEurekaApp {
    public static void main(String[] args) {
        SpringApplication.run(MyEurekaApp.class,args);
    }
}
