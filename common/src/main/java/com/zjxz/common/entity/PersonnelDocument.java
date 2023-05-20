package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资料人员表
 * @TableName personnel_document
 */
@TableName(value ="personnel_document")
@Data
public class PersonnelDocument implements Serializable {
    /**
     * 主键(资料人员表id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 资料基本信息表id
     */
    @TableField(value = "basic_document_id")
    private Long basicDocumentId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 提交时间
     */
    @TableField(value = "submit_time")
    private Date submitTime;

    /**
     * 是否超时(0:未超时;1:已超时)
     */
    @TableField(value = "is_expire")
    private Integer isExpire;

    /**
     * 是否提交(0:未提交;1:已提交)
     */
    @TableField(value = "is_submit")
    private Integer isSubmit;

    /**
     * 文件url
     */
    @TableField(value = "url")
    private String url;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}