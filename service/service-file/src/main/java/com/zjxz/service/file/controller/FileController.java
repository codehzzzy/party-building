package com.zjxz.service.file.controller;

import com.aliyun.oss.OSS;
import com.zjxz.common.entity.PageResult;
import com.zjxz.common.entity.Result;
import com.zjxz.service.file.constants.BucketName;
import com.zjxz.service.file.constants.Common;
import com.zjxz.service.file.utils.MyFileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import static com.zjxz.common.util.PageUtil.getPage;
import static com.zjxz.service.file.constants.MetadataHeader.CONTENT_DISPOSITION;

/**
 * @author hzzzzzy
 * @date 2023/4/5
 * @description 文件控制器
 */
@Tag(name = "1-文件管理")
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private MyFileUtil myFileUtil;
    @Autowired
    private OSS ossClient;

    @SneakyThrows
    @Operation(summary = "下载文件", tags = "接口汇总（下载）")
    @PostMapping("/download")
    @ResponseBody
    public Result download(
            @RequestParam("fileName")
            @Parameter(description = "文件名", required = true)
            String fileName,
            HttpServletResponse response
    ) {
        // 拼接路径
        String path = MyFileUtil.splice(Common.TEST_DIRECTORY_NAME, fileName);
        // 通知浏览器以附件形式下载
        response.setHeader(CONTENT_DISPOSITION,
                "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8.toString()));
        myFileUtil.downloadFile(response.getOutputStream(),path);
        return new Result<>().success().message("下载成功");
    }


    @Operation(summary = "上传文件(普通上传)", tags = "接口汇总（上传）")
    @PostMapping("/upload")
    @ResponseBody
    public Result uploadFile(
            @RequestPart(value = "file")
            @Parameter(description = "文件", required = true)
            MultipartFile file
    ) {
        // 拼接文件名
        String path = MyFileUtil.splice(Common.TEST_DIRECTORY_NAME, file.getOriginalFilename());
        myFileUtil.uploadFile(BucketName.TEST, path, file, true);
        return new Result<>().success().message("上传成功");
    }


    @Operation(summary = "上传文件(模板上传)", tags = "接口汇总（上传）")
    @PostMapping("/uploadTemplate")
    @ResponseBody
    public Result uploadTemplateFile(
            @RequestPart(value = "file")
            @Parameter(description = "模板文件", required = true)
            MultipartFile file
    ) {
        String fileName = file.getOriginalFilename();
        // 拼接url
        String url = MyFileUtil.spliceTemplateUrl(fileName);
        // 上传文件
        myFileUtil.uploadFile(BucketName.TEMPLATE, fileName, file, true);
        // TODO: 存进数据库
        return new Result<>().success().message("上传成功");
    }


    @Operation(summary = "列举指定目录下的目录名称", tags = "接口汇总（列举文件）")
    @PostMapping("/listDirectoryName/{page}/{size}")
    public Result<PageResult<String>> listDirectoryName(
            @PathVariable
            @Parameter(description = "页码", required = true)
            @NotEmpty
            int page,
            @PathVariable
            @Parameter(description = "每页大小", required = true)
            @NotEmpty
            int size,
            @RequestParam("directoryName")
            @Parameter(description = "一个或多个目录名", required = true)
            String...directoryName
    ) {
        // 拼接路径
        String path = MyFileUtil.spliceEndWith(directoryName);
        // 获取目录列表
        List<String> list = myFileUtil.listDirectory(BucketName.TEST, path);
        // 分页
        PageResult<String> result = getPage(list, page, size);
        return new Result<PageResult<String>>().success().message("获取成功").data(result);
    }


    @Operation(summary = "列举指定目录下的所有文件名称", tags = "接口汇总（列举文件）")
    @PostMapping("/listNameByDirectory/{page}/{size}")
    public Result<PageResult<String>> listNameByDirectory(
            @PathVariable
            @Parameter(description = "页码", required = true)
            @NotEmpty
            int page,
            @PathVariable
            @Parameter(description = "每页大小", required = true)
            @NotEmpty
            int size,
            @RequestParam("directoryName")
            @Parameter(description = "一个或多个目录名", required = true)
            String...directoryName
    ) {
        // 拼接路径
        String path = MyFileUtil.spliceEndWith(directoryName);
        // 获取文件列表
        List<String> list = myFileUtil.listFileWithPrefix(BucketName.TEST, path, Common.MAX_KEYS, true);
        // 分页
        PageResult<String> result = getPage(list, page, size);
        return new Result<PageResult<String>>().success().message("获取成功").data(result);
    }


    @Operation(summary = "列举指定前缀的文件名称", tags = "接口汇总（列举文件）")
    @PostMapping("/listFileNameByPrefix/{page}/{size}")
    public Result<PageResult<String>> listFileNameByPrefix(
            @PathVariable
            @Parameter(description = "页码", required = true)
            @NotEmpty
            int page,
            @PathVariable
            @Parameter(description = "每页大小", required = true)
            @NotEmpty
            int size,
            @RequestParam("prefix")
            @Parameter(description = "文件前缀", required = true)
            @NotEmpty
            String prefix,
            @RequestParam("directoryName")
            @Parameter(description = "一个或多个目录名", required = true)
            @NotEmpty
            String... directoryName
    ) {
        // 拼接路径
        String path = MyFileUtil.spliceEndWith(directoryName, prefix);
        // 获取文件列表
        List<String> list = myFileUtil.listFileWithPrefix(BucketName.TEST, path, Common.MAX_KEYS, true);
        // 分页
        PageResult<String> result = getPage(list, page, size);
        return new Result<PageResult<String>>().success().message("获取成功").data(result);
    }
}