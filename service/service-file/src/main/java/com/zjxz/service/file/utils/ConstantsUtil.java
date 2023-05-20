package com.zjxz.service.file.utils;

import com.zjxz.service.file.constants.ActivityName;
import com.zjxz.service.file.constants.BucketName;
import com.zjxz.service.file.constants.PartyBranchName;
import com.zjxz.service.file.constants.StudentIdentity;


/**
 * @author hzzzzzy
 * @date 2023/4/5
 * @description 常量工具类
 */
public class ConstantsUtil {

    /**
     * 桶名称 数组
     */
    public static final String[] BUCKET_NAME = {
            BucketName.ACTIVITY_RECORDS,
            BucketName.SCIENCE_AND_TECHNOLOGY,
            BucketName.TEMPLATE,
            BucketName.TEST
    };

    /**
     * 党支部名称 数组
     */
    public static final String[] PARTY_BRANCH_NAME = {
            PartyBranchName.FIRST_PARTY_BRANCH,
            PartyBranchName.SECOND_PARTY_BRANCH,
            PartyBranchName.THIRD_PARTY_BRANCH,
            PartyBranchName.FOURTH_PARTY_BRANCH,
            PartyBranchName.FIFTH_PARTY_BRANCH,
    };

    /**
     * 活动名称 数组
     */
    public static final String[] ACTIVITY_NAME = {
            ActivityName.BRANCH_PARTY_MEMBER_MEETING,
            ActivityName.BRANCH_COMMITTEE,
            ActivityName.PARTY_GROUP_MEETING,
            ActivityName.PARTY_LECTURE_ACTIVITY,
            ActivityName.THEME_PARTY_ACTIVITY,
            ActivityName.DEMOCRATIC_LIFE_MEETING,
            ActivityName.DEMOCRATIC_EVALUATION_PARTY_MEMBER
    };

    /**
     * 学生身份 数组
     */
    public static final String[] STUDENT_IDENTITY = {
            StudentIdentity.ACTIVIST_OF_PARTY,
            StudentIdentity.PARTY_MEMBER,
            StudentIdentity.PROBATIONARY_PARTY_MEMBER,
            StudentIdentity.PARTY_MEMBER_DEVELOPMENT
    };

    /**
     * 文件目录
     */
    public static final String[] FILE_DIRECTORY = {
            PartyBranchName.FIFTH_PARTY_BRANCH,
            PartyBranchName.SECOND_PARTY_BRANCH,
            PartyBranchName.THIRD_PARTY_BRANCH,
            PartyBranchName.FOURTH_PARTY_BRANCH,
            PartyBranchName.FIFTH_PARTY_BRANCH,
            ActivityName.BRANCH_PARTY_MEMBER_MEETING,
            ActivityName.BRANCH_COMMITTEE,
            ActivityName.PARTY_GROUP_MEETING,
            ActivityName.PARTY_LECTURE_ACTIVITY,
            ActivityName.THEME_PARTY_ACTIVITY,
            ActivityName.DEMOCRATIC_LIFE_MEETING,
            ActivityName.DEMOCRATIC_EVALUATION_PARTY_MEMBER,
            StudentIdentity.ACTIVIST_OF_PARTY,
            StudentIdentity.PARTY_MEMBER,
            StudentIdentity.PROBATIONARY_PARTY_MEMBER,
            StudentIdentity.PARTY_MEMBER_DEVELOPMENT
    };
}
