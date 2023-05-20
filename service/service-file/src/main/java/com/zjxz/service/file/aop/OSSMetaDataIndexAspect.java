package com.zjxz.service.file.aop;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.ObjectMetadata;
import com.zjxz.common.constants.BusinessFailCode;
import com.zjxz.common.entity.Result;
import com.zjxz.common.exception.GlobalException;
import com.zjxz.service.file.constants.BucketName;
import com.zjxz.service.file.constants.Common;
import com.zjxz.service.file.constants.MetadataHeader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

/**
 * @author hzzzzzy
 * @create 2023/4/11
 * @description 添加元数据索引 切面类
 */
@Aspect
@Component
public class OSSMetaDataIndexAspect {

    @Autowired
    private OSS ossClient;

    @Before("@annotation(EnableOssMetadataIndexing)")
    public void beforeUploadFile(JoinPoint joinPoint) {
        String objectName = (String) joinPoint.getArgs()[0];
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(MetadataHeader.META_INDEX, Common.TRUE);
        judgeType(joinPoint, objectName, metadata, ossClient);
    }

    @After("@annotation(EnableOssMetadataIndexing)")
    public void afterUploadFile(JoinPoint joinPoint) {
        String name = joinPoint.getArgs()[0].getClass().getName();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setHeader(MetadataHeader.META_INDEX, Common.FALSE);
        judgeType(joinPoint, name, metadata, ossClient);
    }

    /**
     * 判断文件类型
     *
     * @param joinPoint
     * @param objectName
     * @param metadata
     * @param ossClient
     */
    private void judgeType(JoinPoint joinPoint, String objectName, ObjectMetadata metadata, OSS ossClient) {
        if (joinPoint.getArgs()[1] instanceof java.io.File) {
            ossClient.putObject(BucketName.TEST, objectName, (java.io.File) joinPoint.getArgs()[1], metadata);
        } else if (joinPoint.getArgs()[1] instanceof InputStream) {
            ossClient.putObject(BucketName.TEST, objectName, (InputStream) joinPoint.getArgs()[1], metadata);
        } else {
            throw new GlobalException(new Result<>().error(BusinessFailCode.OBJECT_TYPE_ERROR).message("文件类型错误，添加元数据索引失败"));
        }
    }
}
