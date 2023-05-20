package com.zjxz.service.file.utils;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.zjxz.common.constants.BusinessFailCode;
import com.zjxz.common.entity.Result;
import com.zjxz.common.exception.GlobalException;
import com.zjxz.service.file.constants.BucketName;
import com.zjxz.service.file.constants.Common;
import com.zjxz.service.file.constants.MetadataHeader;
import com.zjxz.service.file.model.dto.ContentTypeExcel;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author hzzzzzy
 * @date 2023/4/5
 * @description 文件工具类
 */
@Component
@Slf4j
public class MyFileUtil {

    @Autowired
    private OSS ossClient;

    /**
     * 拼接阿里云链接url
     *
     * @param fileName
     * @return
     */
    public static String spliceTemplateUrl(String fileName) {
        return Common.TEMPLATE_URL_PREFIX + fileName;
    }

    /**
     * 判断桶名称是否存在
     *
     * @param bucketName 桶名称
     */
    public void isBucketExist(String bucketName) {
        // FIXME 待测试
        if (!ArrayUtil.contains(ConstantsUtil.BUCKET_NAME, bucketName)) {
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("桶名称不符合规范"));
        }
        if (!ossClient.doesBucketExist(bucketName)) {
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("桶名称不存在"));
        }
    }


    /**
     * 判断目录是否存在
     *
     * @param directoryName 目录名称
     * @param bucketName 桶名称
     */
    public void isDirectoryExist(String bucketName, String directoryName) {
        // FIXME 待测试
        // 判断oss中是否存在该目录
        boolean isExist = ossClient.doesObjectExist(bucketName, directoryName);
        if (!isExist) {
            throw new GlobalException(new Result<>().error(BusinessFailCode.PARAMETER_ERROR).message("目录不存在"));
        }
    }


    /**
     * 创建目录
     *
     * @param bucketName 桶名称
     * @param directoryName 目录名称
     */
    public void createDirectory(String bucketName, String directoryName){
        ossClient.putObject(bucketName, directoryName, new ByteArrayInputStream(new byte[0]));
    }


    /**
     * 删除文件
     *
     * @param bucketName 桶名称
     * @param path 路径
     */
    public void deleteFile(String bucketName, String path) {
        // FIXME 待测试
        // 判断桶名称是否存在
        isBucketExist(bucketName);
        // 删除文件
        ossClient.deleteObject(bucketName, path);
    }


    /**
     * 删除目录以及目录下所有文件
     */
    @SneakyThrows
    public void deleteDirectory(String bucketName, String directoryName) {
        // FIXME 存在bug 4/11
        // 判断桶名称是否存在
        isBucketExist(bucketName);
        // 判断目录是否存在
        isDirectoryExist(bucketName, directoryName);
        // 删除目录及目录下的所有文件。
        String nextMarker = null;
        ObjectListing objectListing = null;
        do {
            ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName)
                    .withPrefix(directoryName)
                    .withMarker(nextMarker);
            objectListing = ossClient.listObjects(listObjectsRequest);
            if (objectListing.getObjectSummaries().size() > 0) {
                List<String> keys = new ArrayList<>();
                for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
                    keys.add(s.getKey());
                }
                DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(keys).withEncodingType("url");
                DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
                List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
                for(String obj : deletedObjects) {
                    String deleteObj =  URLDecoder.decode(obj, "UTF-8");
                    log.info("删除文件成功: {}", deleteObj);
                }
            }
            nextMarker = objectListing.getNextMarker();
        } while (objectListing.isTruncated());
    }


    /**
     * 拼接路径，以/结尾
     *
     * @param name 名称
     * @return 拼接后的路径
     */
    public static String spliceEndWith(String... name) {
        StringBuilder path = new StringBuilder();
        Arrays.stream(name).collect(Collectors.toList()).forEach(directory -> path.append(directory).append("/"));
        return path.toString();
    }

    /**
     * 拼接路径，以/结尾
     *
     * @param name 名称
     * @return 拼接后的路径
     */
    public static String spliceEndWith(String[] name, String prefix) {
        StringBuilder path = new StringBuilder();
        Arrays.stream(name).collect(Collectors.toList()).forEach(directory -> path.append(directory).append("/"));
        path.append(prefix);
        return path.toString();
    }


    /**
     * 拼接路径，不以/结尾
     *
     * @param name 名称
     * @return 拼接后的路径
     */
    public static String splice(String... name) {
        StringBuilder path = new StringBuilder();
        Arrays.stream(name).collect(Collectors.toList()).forEach(directory -> path.append(directory).append("/"));
        path.deleteCharAt(path.length() - 1);
        return path.toString();
    }


    /**
     * 列举指定目录下的所有文件
     *
     * @param bucketName 桶名称
     * @param directoryName 目录名称
     * @param maxKeys 最大个数
     * @param flag 是否需要解码
     * @return 文件列表
     */
    @SneakyThrows
    public List<String> listFileWithPrefix(String bucketName, String directoryName, int maxKeys, boolean flag) {
        isBucketExist(bucketName);
        ListObjectsRequest condition = new ListObjectsRequest(bucketName).withPrefix(directoryName).withMaxKeys(maxKeys);
        condition.setDelimiter("/");
        if (flag){
            condition.setEncodingType("url");
        }
        ObjectListing objectListing = ossClient.listObjects(condition);
        List<String> fileNameList = new ArrayList<>();
        if (flag){
            do {
                // 文件名称解码
                for (OSSObjectSummary objectSummary: objectListing.getObjectSummaries()) {
                    String key = objectSummary.getKey();
                    String decodedKey = URLDecoder.decode(key, StandardCharsets.UTF_8.toString());
                    fileNameList.add(decodedKey);
                }
            }while (objectListing.isTruncated());
        }
        if (BeanUtil.isEmpty(fileNameList)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("指定前缀的文件不存在"));
        }
        List<String> res = fileNameList.stream().map(fileName -> {
            String[] split = fileName.split("/");
            return split[split.length - 1];
        }).collect(Collectors.toList());
        return res;
    }


    /**
     * 列举指定目录下的所有目录
     *
     * @param bucketName 桶名称
     * @param directoryName 目录名称
     * @return 文件列表
     */
    public List<String> listDirectory(String bucketName, String directoryName) {
        // FIXME 待测试
        isBucketExist(bucketName);
        ListObjectsRequest condition = new ListObjectsRequest(bucketName);
        condition.setPrefix(directoryName);
        condition.setDelimiter("/");
        ObjectListing objectListing = ossClient.listObjects(condition);
        List<String> list = objectListing.getCommonPrefixes();
        if (BeanUtil.isEmpty(list)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("指定目录下没有目录"));
        }
        List<String> res = list.stream().map(directory -> {
            String[] split = directory.split("/");
            return split[split.length - 1];
        }).collect(Collectors.toList());
        return res;
    }


    /**
     * 上传文件
     *
     * @param bucketName 桶名称
     * @param flag 是否需要覆盖同名的object
     * @param file 文件
     * @return 文件路径
     */
    @SneakyThrows
    public void uploadFile(String bucketName, String path, MultipartFile file, boolean flag) {
        isBucketExist(bucketName);
        ObjectMetadata metadata = new ObjectMetadata();
        if (flag){
            metadata.setHeader(MetadataHeader.OVERWRITE, Common.TRUE);
        }
        metadata.setContentType(getContentType(file.getOriginalFilename()));
        ossClient.putObject(bucketName, path, file.getInputStream());
    }


    /***
     * 获取文件类型
     *
     * @param fileName 文件名称
     * @return 文件类型
     */
    @SneakyThrows
    public static String getContentType(String fileName) {
        // 获取文件后缀
        String suffix = getFileSuffix(fileName);
        // 读取excel文件
        AtomicReference<String> type = new AtomicReference<>("");
        String excelPath = ResourceUtils.getFile("classpath:" + "ContentType.xlsx").getPath();
        EasyExcel.read(excelPath, ContentTypeExcel.class, new PageReadListener<ContentTypeExcel>(list -> list.forEach(contentTypeExcel -> {
            if (contentTypeExcel.getFileExtension().equals(suffix)){
                type.set(contentTypeExcel.getType());
            }
        }))).sheet().doRead();
        log.info("文件contentType类型为: {}", type);
        return type.get();
    }


    /**
     * 下载文件
     *
     * @param os outputStream流
     * @param path 路径
     * @return 文件流
     */
    @SneakyThrows
    public void downloadFile(OutputStream os, String path){
        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(BucketName.TEST, path);
        // 读取文件内容
        BufferedInputStream in = new BufferedInputStream(ossObject.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        if (out != null) {
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }


    /**
     * 创建软链接
     *
     * @param bucketName 桶名称
     * @param contentType 文件类型
     * @param path 路径(不包含桶名称)
     * @param symLink 软链接
     */
    public void createSymlink(String bucketName, String contentType, String path, String symLink) {
        // FIXME 待测试
        isBucketExist(bucketName);
        // 创建上传文件元信息
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        // 设置元信息
        metadata.addUserMetadata("property", "property-value");
        CreateSymlinkRequest symlinkRequest = new CreateSymlinkRequest(bucketName, symLink, path);
        symlinkRequest.setMetadata(metadata);
        // 创建软链接
        ossClient.createSymlink(symlinkRequest);
    }


    /**
     * 获取软链接
     *
     * @param bucketName 桶名称
     * @param symLink 软链接
     * @return 目标文件名称
     */
    public String getSymlink(String bucketName, String symLink) {
        // FIXME 待测试
        isBucketExist(bucketName);
        // 获取软链接
        OSSSymlink ossSymlink = ossClient.getSymlink(bucketName, symLink);
        // 获取软链接的目标文件
        if (BeanUtil.isEmpty(ossSymlink)){
            throw new GlobalException(new Result<>().error(BusinessFailCode.DATA_FETCH_ERROR).message("软链接不存在"));
        }
        return ossSymlink.getTarget();
    }


    /**
     * 获取文件后缀
     *
     * @param fileName 文件名称
     * @return 文件后缀
     */
    public static String getFileSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
