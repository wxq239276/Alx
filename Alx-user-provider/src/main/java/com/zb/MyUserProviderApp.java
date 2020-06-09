package com.zb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zb.mapper")
public class MyUserProviderApp {
    public static void main(String[] args) {
        SpringApplication.run(MyUserProviderApp.class, args);
    }
}
