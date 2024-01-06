package com.huangli.web.cache;

import com.huangli.web.domain.SysUserLogin;
import com.huangli.web.utils.ExpireData;

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
     * 用户默认过期时间: 15分钟
     **/
    public static Long DEFAULT_USER_EXPIRE_TIME = 15 * 60L;
    /**
     * 已登录用户
     * @Author liuxb
     * @Date 2024/1/5
     **/
    public static ConcurrentHashMap<String, ExpireData<SysUserLogin>> LOGIN_USER = new ConcurrentHashMap<>();
}
