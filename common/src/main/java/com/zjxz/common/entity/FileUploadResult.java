package com.zjxz.common.entity;

import lombok.Data;

/**
 * @author hzzzzzy
 * @create 2023/4/2
 * @description 文件上传通用返回类
 */
@Data
public class FileUploadResult {
    /**
     * 文件唯一标识
     */
    private String uid;

    /**
     * 文件名
     */
    private String name;
}
