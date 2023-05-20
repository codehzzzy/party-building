package com.zjxz.common.util;

import cn.hutool.core.bean.BeanUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hzzzzzy
 * @create 2023/4/1
 * @description 将EntityList转为对应的VOList
 */
public class ListUtil {
    public static <Eneity,VO> List<VO> eneity2VO(List<Eneity> eneityList, Class<VO> vo){
        return eneityList.stream().map(item -> BeanUtil.copyProperties(item, vo)).collect(Collectors.toList());
    }
}