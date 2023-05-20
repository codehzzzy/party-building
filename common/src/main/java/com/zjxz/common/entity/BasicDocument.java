package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 资料基本信息表
 * @TableName basic_document
 */
@TableName(value ="basic_document")
@Data
public class BasicDocument implements Serializable {
    /**
     * 主键(资料基本信息表id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支委会id
     */
    @TableField(value = "branch_committee_id")
    private Long branchCommitteeId;

    /**
     * 截止日期
     */
    @TableField(value = "deadline")
    private Date deadline;

    /**
     * 资料名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 资料描述
     */
    @TableField(value = "description")
    private String description;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}