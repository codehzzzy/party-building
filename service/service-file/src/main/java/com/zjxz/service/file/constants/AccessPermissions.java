package com.zjxz.service.file.constants;

/**
 * @author hzzzzzy
 * @date 2023/4/11
 * @description 访问权限常量类
 */
public interface AccessPermissions {
    /**
     * 公共读
     */
    String PUBLIC_READ = "public-read";

    /**
     * 公共读写
     */
    String PUBLIC_READ_WRITE = "public-read-write";

    /**
     * 私有(只有该Object的Owner拥有该Object的读写权限，其他用户没有权限操作该Object)
     */
    String PRIVATE = "private";

    /**
     * 默认(遵循其所在Bucket的读写权限)
     */
    String DEFAULT = "default";
}
