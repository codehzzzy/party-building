CREATE DATABASE party_building CHARACTER SET utf8;
use party_building;

/*模板资料表*/
DROP TABLE IF EXISTS `template`;
CREATE TABLE `template`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(模板id)',
    `template_name` varchar(255)    NOT NULL COMMENT '模板名称',
    `url`           varchar(255)    NOT NULL COMMENT '文件url'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '模板表';

/*学生表*/
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(学生id)',
    `avatar`        varchar(255)    COMMENT '头像',
    `clazz`         varchar(255)    NOT NULL COMMENT '班级',
    `name`          varchar(255)    NOT NULL COMMENT '学生姓名',
    `sex`           varchar(255)    NOT NULL COMMENT '学生性别',
    `phone`         varchar(255)    NOT NULL COMMENT '联系电话',
    `dormitory`     varchar(255)    NOT NULL COMMENT '宿舍',
    `birth`         datetime        NOT NULL COMMENT '出生年月',
    `identity`      tinyint         NOT NULL COMMENT '当前身份(1:积极分子;2:发展对象;3:预备党员;4:正式党员)',
    `apply_time`    datetime        NOT NULL COMMENT '申请入党时间',
    `confirm_time_1`    datetime    NOT NULL COMMENT '确定为积极分子的时间',
    `confirm_time_2`    datetime    COMMENT '确定为发展对象的时间',
    `confirm_time_3`    datetime    COMMENT '确定为预备党员的时间',
    `confirm_time_4`    datetime    COMMENT '确定为党员的时间',
    `party_branch_id`   bigint      NOT NULL COMMENT '支部id',
    `number`            bigint      NOT NULL COMMENT '学号',
    `password`          varchar(255)     NOT NULL COMMENT '密码',
    `party_group_id`    bigint      DEFAULT 0 COMMENT '党小组id',
    `cultivate_person_1`  varchar(255)   COMMENT '培养联系人1',
    `cultivate_person_2`  varchar(255)   COMMENT '培养联系人2',
    `introducer_1`        varchar(255)   COMMENT '入党介绍人1',
    `introducer_2`        varchar(255)   COMMENT '入党介绍人2',
    `party_job`           varchar(255)   COMMENT '党内职务',
    `pre_job`             varchar(255)   COMMENT '担任职务',
    `is_graduate`         varchar(255)   DEFAULT 0 COMMENT '是否毕业(0:未毕业;1:已毕业)'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '学生表';

/*党小组表*/
DROP TABLE IF EXISTS `party_group`;
CREATE TABLE `party_group`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(党小组id)',
    `name`          varchar(255)    NOT NULL COMMENT '党小组名称',
    `party_branch_id`   bigint      NOT NULL COMMENT '支部id'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '党小组表';

/*支部表*/
DROP TABLE IF EXISTS `party_branch`;
CREATE TABLE `party_branch`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(支部id)',
    `party_branch_name`      varchar(255)    NOT NULL COMMENT '支部名称'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '支部表';

/*支委会成员表*/
DROP TABLE IF EXISTS `branch_committee`;
CREATE TABLE `branch_committee`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(支委会成员id)',
    `office`        varchar(255)    NOT NULL COMMENT '支委会职务',
    `party_branch_id`   bigint      NOT NULL COMMENT '支部id',
    `account`       bigint          NOT NULL COMMENT '账号',
    `password`          varchar(255)     NOT NULL COMMENT '密码'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '支委会成员表';

/*志愿时长表*/
DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(志愿时长id)',
    `user_id`       bigint          NOT NULL COMMENT '用户id',
    `volunteer_name`varchar(255)    NOT NULL COMMENT '志愿名称',
    `pre_time`      datetime        NOT NULL COMMENT '开始时间',
    `end_time`      datetime        NOT NULL COMMENT '结束时间',
    `volunteer_time`    bigint      NOT NULL COMMENT '志愿时长(分钟)',
    `url`           varchar(255)    NOT NULL COMMENT '图片url',
    `inspection_time `  bigint      DEFAULT 0 COMMENT '检测时长',
    `is_audit`      tinyint         NOT NULL DEFAULT 0 COMMENT '是否审核(0:未审核;1:审核成功;2:审核失败)'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '志愿时长表';

/*强国积分表*/
DROP TABLE IF EXISTS `bonus_point`;
CREATE TABLE `bonus_point`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(强国积分id)',
    `user_id`       bigint          NOT NULL COMMENT '用户id',
    `points`        bigint          NOT NULL COMMENT '积分',
    `url`           varchar(255)    NOT NULL COMMENT '图片url',
    `is_valid`      tinyint         NOT NULL DEFAULT 0 COMMENT '是否审核(0:未审核;1:审核成功;2:审核失败)',
    `create_time`   datetime        DEFAULT CURRENT_TIMESTAMP NOT NULL comment '创建时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '强国积分表';

/*资料基本信息表*/
DROP TABLE IF EXISTS `basic_document`;
CREATE TABLE `basic_document`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(资料基本信息表id)',
    `branch_committee_id`   bigint  NOT NULL COMMENT '支委会id',
    `deadline`      datetime        NOT NULL COMMENT '截止日期',
    `name`          varchar(255)    NOT NULL COMMENT '资料名称',
    `description`   varchar(255)    COMMENT '资料描述'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '资料基本信息表';

/*资料人员表*/
DROP TABLE IF EXISTS `personnel_document`;
CREATE TABLE `personnel_document`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(资料人员表id)',
    `basic_document_id` bigint      NOT NULL COMMENT '资料基本信息表id',
    `user_id`       bigint          NOT NULL COMMENT '用户id',
    `submit_time`   datetime        COMMENT '提交时间',
    `is_expire`     tinyint         COMMENT '是否超时(0:未超时;1:已超时)',
    `is_submit`     tinyint         NOT NULL DEFAULT 0 COMMENT '是否提交(0:未提交;1:已提交)',
    `url`           varchar(255)    COMMENT '文件url'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '资料人员表';

/*活动表*/
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(活动id)',
    `branch_committee_id`  bigint   NOT NULL COMMENT '支委会成员id',
    `name`          varchar(255)    NOT NULL COMMENT '活动名称',
    `description`   varchar(255)    NOT NULL COMMENT '活动描述',
    `pre_time`      datetime        NOT NULL COMMENT '开始时间',
    `end_time`      datetime        NOT NULL COMMENT '结束时间',
    `is_record_learning_hours` tinyint NOT NULL COMMENT '是否记录学时(1:记录;2:不记录)',
    `is_record_grades`  tinyint     NOT NULL COMMENT '是否记录成绩(1:记录;2:不记录)',
    `activity_hours`tinyint         NOT NULL COMMENT '活动学时'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '活动表';

/*活动人员表*/
DROP TABLE IF EXISTS `activity_records`;
CREATE TABLE `activity_records`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(中间表id)',
    `user_id`       bigint          NOT NULL COMMENT '用户id',
    `activity_id`   bigint          NOT NULL COMMENT '活动id',
    `grades`        tinyint         DEFAULT 0 COMMENT '成绩',
    `learning_hours`tinyint         DEFAULT 0 COMMENT '获得的学时',
    `type`          tinyint         NOT NULL DEFAULT 0 COMMENT '响应类型(0:未响应;1:参加;2:病假;3:事假;4:课程冲突)',
    `is_attendance` tinyint         NOT NULL DEFAULT 0 COMMENT '是否签到(0:未开始;1:已签到;2:未签到)'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '活动人员表';

/*转变表*/
DROP TABLE IF EXISTS `change_identity`;
CREATE TABLE `change_identity`
(
    `id`            bigint          AUTO_INCREMENT PRIMARY KEY COMMENT '主键(中间表id)',
    `user_id`       bigint          NOT NULL COMMENT '用户id',
    `identity`      tinyint         NOT NULL COMMENT '当前身份',
    `is_change`     tinyint         NOT NULL DEFAULT 1 COMMENT '是否转变(1:待定;2:转变;3不转变)'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT '转变表';