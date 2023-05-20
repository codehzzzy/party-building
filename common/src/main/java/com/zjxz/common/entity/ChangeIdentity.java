package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 转变表
 * @TableName change_identity
 */
@TableName(value ="change_identity")
@Data
public class ChangeIdentity implements Serializable {
    /**
     * 主键(中间表id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 当前身份
     */
    @TableField(value = "identity")
    private Integer identity;

    /**
     * 是否转变(1:待定;2:转变;3不转变)
     */
    @TableField(value = "is_change")
    private Integer isChange;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}