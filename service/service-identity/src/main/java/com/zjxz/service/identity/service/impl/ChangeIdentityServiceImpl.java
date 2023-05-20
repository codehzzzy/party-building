package com.zjxz.service.identity.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjxz.common.entity.ChangeIdentity;
import com.zjxz.service.identity.mapper.ChangeIdentityMapper;
import com.zjxz.service.identity.service.ChangeIdentityService;
import org.springframework.stereotype.Service;

/**
* @author 29392
* @description 针对表【change_identity(转变表)】的数据库操作Service实现
* @createDate 2023-05-04 20:53:09
*/
@Service
public class ChangeIdentityServiceImpl extends ServiceImpl<ChangeIdentityMapper, ChangeIdentity>
    implements ChangeIdentityService {

    @Override
    public boolean applyToChange(Long studentId, Integer identity) {
        return true;
    }

    @Override
    public boolean auditIdentity(Long studentId, Boolean isSuccess) {
        return true;
    }
}




