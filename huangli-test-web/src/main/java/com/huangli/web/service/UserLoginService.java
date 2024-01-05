package com.huangli.web.service;

import com.huangli.web.vo.BaseLoginVO;

import java.util.Map;

/**
 * @author lxb
 * @version 1.0
 * @title UserLoginService
 * @description 用户登录退出业务类
 * @create 2024/1/5 17:59
 */
public interface UserLoginService {
    /**
     * 用户登录
     * @Author liuxb
     * @param: loginVO
     * @Return java.lang.Object
     * @Date 2024/1/5
     **/
    Map<String, Object> login(BaseLoginVO loginVO);
}
