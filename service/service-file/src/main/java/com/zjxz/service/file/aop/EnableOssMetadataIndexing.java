package com.zjxz.service.file.aop;

import java.lang.annotation.*;

/**
 * @author hzzzzzy
 * @create 2023/4/11
 * @description 添加元数据索引 注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableOssMetadataIndexing {
}
