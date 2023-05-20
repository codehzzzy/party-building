package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 党小组表
 * @TableName party_group
 */
@TableName(value ="party_group")
@Data
public class PartyGroup implements Serializable {
    /**
     * 主键(党小组id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 党小组名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 支部id
     */
    @TableField(value = "party_branch_id")
    private Long partyBranchId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}