package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 支委会成员表
 * @TableName branch_committee
 */
@TableName(value ="branch_committee")
@Data
public class BranchCommittee implements Serializable {
    /**
     * 主键(支委会成员id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支委会职务
     */
    @TableField(value = "office")
    private String office;

    /**
     * 支部id
     */
    @TableField(value = "party_branch_id")
    private Long partyBranchId;

    /**
     * 账号
     */
    @TableField(value = "account")
    private Long account;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}