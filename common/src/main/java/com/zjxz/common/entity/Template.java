package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 模板表
 * @TableName template
 */
@TableName(value ="template")
@Data
public class Template implements Serializable {
    /**
     * 主键(模板id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 模板名称
     */
    @TableField(value = "template_name")
    private String templateName;

    /**
     * 文件url
     */
    @TableField(value = "url")
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}