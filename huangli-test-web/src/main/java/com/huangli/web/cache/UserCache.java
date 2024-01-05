package com.huangli.web.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lxb
 * @version 1.0
 * @title UserCache
 * @description
 * @create 2024/1/5 18:14
 */
public class UserCache {
    /**
     * 用户登录次数记录缓存
     * @Author liuxb
     * @Date 2024/1/5
     **/
    public static ConcurrentHashMap<String, Integer> LOGIN_CNT_CACHE = new ConcurrentHashMap<>();
}
