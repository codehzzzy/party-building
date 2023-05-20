package com.zjxz.service.file;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author hzzzzzy
 * @date 2023/4/6
 * @description 文件启动类
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
@EnableFeignClients
public class FileApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileApplication.class, args);
    }
}
