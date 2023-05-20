package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 支部表
 * @TableName party_branch
 */
@TableName(value ="party_branch")
@Data
public class PartyBranch implements Serializable {
    /**
     * 主键(支部id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支部名称
     */
    @TableField(value = "party_branch_name")
    private String partyBranchName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}