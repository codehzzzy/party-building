package com.zjxz.service.file.config;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.context.annotation.Bean;

/**
 * @author hzzzzzy
 * @date 2023/4/14
 * @description CommonsMultipartResolver
 */

@Component
public class MyCommonsMultipartResolver {
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setMaxUploadSize(104857600);
        commonsMultipartResolver.setMaxInMemorySize(40960);
        commonsMultipartResolver.setDefaultEncoding("UTF-8");
        return commonsMultipartResolver;
    }
}