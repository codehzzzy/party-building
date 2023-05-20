package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 活动人员表
 * @TableName personnel_activity
 */
@TableName(value ="personnel_activity")
@Data
public class PersonnelActivity implements Serializable {
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
     * 活动id
     */
    @TableField(value = "activity_id")
    private Long activityId;

    /**
     * 成绩
     */
    @TableField(value = "grades")
    private Integer grades;

    /**
     * 获得的学时
     */
    @TableField(value = "learning_hours")
    private Integer learningHours;

    /**
     * 响应类型(0:未响应;1:参加;2:病假;3:事假;4:课程冲突)
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 是否签到(0:未开始;1:已签到;2:未签到)
     */
    @TableField(value = "is_attendance")
    private Integer isAttendance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}