package com.zjxz.service.identity.controller;
import com.zjxz.common.constants.BusinessFailCode;
import com.zjxz.common.entity.Result;
import com.zjxz.common.exception.GlobalException;
import com.zjxz.service.identity.service.ChangeIdentityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hzzzzzy
 * @date 2023/4/26
 * @description ManageController
 */
@Api("xxx")
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/manage")
public class ManageController {
    @Autowired
    private ChangeIdentityService changeIdentityService;

    @ApiOperation("学生申请身份转变")
    @PostMapping("/changeIdentity")
    public Result changeIdentity(
            @RequestParam("studentId")
            @Parameter(description = "学生id", required = true)
            Long studentId,
            @RequestParam("identity")
            @Parameter(description = "当前身份(1:积极分子;2:发展对象;3:预备党员;4:党员)", required = true)
            Integer identity
    ) {
        boolean flag = changeIdentityService.applyToChange(studentId, identity);
        if (!flag) {
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("申请失败"));
        }
        return new Result<>().success().message("申请成功");
    }


    @ApiOperation("审核身份转变")
    @PostMapping("/auditIdentity/{flag}")
    public Result auditIdentity(
            @RequestParam("studentId")
            @Parameter(description = "学生id", required = true)
            Long studentId,
            @PathVariable("isSuccess")
            @Parameter(description = "审核是否成功", required = true)
            Boolean isSuccess
    ) {
        boolean flag = changeIdentityService.auditIdentity(studentId, isSuccess);
        if (!flag) {
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("转变失败"));
        }
        return new Result<>().success().message("转变成功");
    }
}