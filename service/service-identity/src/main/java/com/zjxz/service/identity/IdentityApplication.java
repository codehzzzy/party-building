package com.zjxz.service.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author hzzzzzy
 * @date 2023/4/6
 * @description 文件启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
@EnableFeignClients
@EnableScheduling
public class IdentityApplication {
    public static void main(String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }
}
