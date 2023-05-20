package com.zjxz.service.identity.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjxz.common.entity.ChangeIdentity;

/**
* @author 29392
* @description 针对表【change_identity(转变表)】的数据库操作Service
* @createDate 2023-05-04 20:53:09
*/
public interface ChangeIdentityService extends IService<ChangeIdentity> {

    /**
     * 学生申请身份转变
     *
     * @param studentId 学生id
     * @param identity 当前身份(1:积极分子;2:发展对象;3:预备党员;4:党员)
     * @return 是否成功
     */
    boolean applyToChange(Long studentId, Integer identity);

    /**
     * 审核身份转变
     *
     * @param studentId 学生id
     * @param isSuccess 是否通过
     * @return 是否成功
     */
    boolean auditIdentity(Long studentId, Boolean isSuccess);
}
