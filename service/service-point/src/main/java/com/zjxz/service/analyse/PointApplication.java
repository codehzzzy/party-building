package com.zjxz.service.analyse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author hzzzzzy
 * @date 2023/4/7
 * @description PointApplication
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PointApplication {
    public static void main(String[] args) {
        SpringApplication.run(PointApplication.class, args);
    }
}
