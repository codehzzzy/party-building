package com.zjxz.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学生表
 * @TableName student
 */
@TableName(value ="student")
@Data
public class Student implements Serializable {
    /**
     * 主键(学生id)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 班级
     */
    @TableField(value = "clazz")
    private String clazz;

    /**
     * 学生姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 学生性别
     */
    @TableField(value = "sex")
    private String sex;

    /**
     * 联系电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 宿舍
     */
    @TableField(value = "dormitory")
    private String dormitory;

    /**
     * 出生年月
     */
    @TableField(value = "birth")
    private Date birth;

    /**
     * 当前身份(1:积极分子;2:发展对象;3:预备党员;4:正式党员)
     */
    @TableField(value = "identity")
    private Integer identity;

    /**
     * 申请入党时间
     */
    @TableField(value = "apply_time")
    private Date applyTime;

    /**
     * 确定为积极分子的时间
     */
    @TableField(value = "confirm_time_1")
    private Date confirmTime1;

    /**
     * 确定为发展对象的时间
     */
    @TableField(value = "confirm_time_2")
    private Date confirmTime2;

    /**
     * 确定为预备党员的时间
     */
    @TableField(value = "confirm_time_3")
    private Date confirmTime3;

    /**
     * 确定为党员的时间
     */
    @TableField(value = "confirm_time_4")
    private Date confirmTime4;

    /**
     * 支部id
     */
    @TableField(value = "party_branch_id")
    private Long partyBranchId;

    /**
     * 学号
     */
    @TableField(value = "number")
    private Long number;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 党小组id
     */
    @TableField(value = "party_group_id")
    private Long partyGroupId;

    /**
     * 培养联系人1
     */
    @TableField(value = "cultivate_person_1")
    private String cultivatePerson1;

    /**
     * 培养联系人2
     */
    @TableField(value = "cultivate_person_2")
    private String cultivatePerson2;

    /**
     * 入党介绍人1
     */
    @TableField(value = "introducer_1")
    private String introducer1;

    /**
     * 入党介绍人2
     */
    @TableField(value = "introducer_2")
    private String introducer2;

    /**
     * 党内职务
     */
    @TableField(value = "party_job")
    private String partyJob;

    /**
     * 担任职务
     */
    @TableField(value = "pre_job")
    private String preJob;

    /**
     * 是否毕业(0:未毕业;1:已毕业)
     */
    @TableField(value = "is_graduate")
    private String isGraduate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}