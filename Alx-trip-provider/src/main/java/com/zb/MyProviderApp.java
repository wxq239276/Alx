package com.zb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author 范杰
 * @Description TODO
 * @Date 2020/4/21
 * @Version V1.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zb.mapper")
public class MyProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(MyProviderApp.class,args);
    }
}
