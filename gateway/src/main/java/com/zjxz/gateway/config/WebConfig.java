package com.zjxz.gateway.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import java.text.DateFormat;

/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description 自定义DateFormat
 */
@Configuration
public class WebConfig {
    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;
    @Bean
    public MappingJackson2HttpMessageConverter MappingJsonpHttpMessageConverter() {
        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
        DateFormat dateFormat = mapper.getDateFormat();
        mapper.setDateFormat(new MyDateFormat(dateFormat));
        MappingJackson2HttpMessageConverter mappingJsonpHttpMessageConverter = new MappingJackson2HttpMessageConverter(mapper);
        return mappingJsonpHttpMessageConverter;
    }
}
