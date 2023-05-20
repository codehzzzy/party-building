package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 强国积分表
 * @TableName bonus_point
 */
@TableName(value ="bonus_point")
@Data
public class BonusPoint implements Serializable {
    /**
     * 主键(强国积分id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 积分
     */
    @TableField(value = "points")
    private Long points;

    /**
     * 图片url
     */
    @TableField(value = "url")
    private String url;

    /**
     * 是否审核(0:未审核;1:审核成功;2:审核失败)
     */
    @TableField(value = "is_valid")
    private Integer isValid;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}