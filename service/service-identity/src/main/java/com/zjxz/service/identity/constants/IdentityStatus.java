package com.zjxz.service.identity.constants;

/**
 * @author hzzzzzy
 * @date 2023/5/4
 * @description 身份状态
 */
public interface IdentityStatus {
    /**
     * 待定
     */
    Integer WAIT = 1;

    /**
     * 转变
     */
    Integer CHANGE = 2;

    /**
     * 不转变
     */
    Integer NOT_CHANGE = 3;
}
