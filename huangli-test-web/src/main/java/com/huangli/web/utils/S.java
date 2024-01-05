package com.huangli.web.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @author lxb
 * @version 1.0
 * @title S
 * @description 扩展字符utils
 * @create 2024/1/5 18:10
 */
public class S extends StrUtil {
    /**
     * 判断两个字符串是否相等，增加空值判断，防止空指针异常
     * @Author liuxb
     * @param: s1
     * @param: s2
     * @Return java.lang.Boolean
     * @Date 2024/1/5
     **/
    public static Boolean isEq(String s1, String s2) {
        if (isBlank(s1) || isBlank(s2)) {
            return false;
        }
        return s1.equals(s2);
    }
}
