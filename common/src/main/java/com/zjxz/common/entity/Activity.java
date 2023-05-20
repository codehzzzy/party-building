package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 活动表
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    /**
     * 主键(活动id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 支委会成员id
     */
    @TableField(value = "branch_committee_id")
    private Long branchCommitteeId;

    /**
     * 活动名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 活动描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 开始时间
     */
    @TableField(value = "pre_time")
    private Date preTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    private Date endTime;

    /**
     * 是否记录学时(1:记录;2:不记录)
     */
    @TableField(value = "is_record_learning_hours")
    private Integer isRecordLearningHours;

    /**
     * 是否记录成绩(1:记录;2:不记录)
     */
    @TableField(value = "is_record_grades")
    private Integer isRecordGrades;

    /**
     * 活动学时
     */
    @TableField(value = "activity_hours")
    private Integer activityHours;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}