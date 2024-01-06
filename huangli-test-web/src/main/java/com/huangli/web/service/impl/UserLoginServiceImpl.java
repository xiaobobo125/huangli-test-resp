package com.huangli.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.huangli.web.cache.UserCache;
import com.huangli.web.domain.BaseEntity;
import com.huangli.web.domain.SysUserLogin;
import com.huangli.web.exception.BusinessException;
import com.huangli.web.mapper.SysUserLoginMapper;
import com.huangli.web.service.UserLoginService;
import com.huangli.web.utils.BaseEntityUtils;
import com.huangli.web.utils.ExpireData;
import com.huangli.web.utils.S;
import com.huangli.web.vo.BaseLoginVO;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lxb
 * @version 1.0
 * @title UserLoginServiceImpl
 * @description 用户登录退出业务类
 * @create 2024/1/5 17:59
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Resource
    private SysUserLoginMapper userLoginMapper;
    /**
     * 用户登录
     * @Author liuxb
     * @param: loginVO
     * @Return java.util.Map<java.lang.String,java.lang.Object>
     * @Date 2024/1/5
     **/
    @Override
    public Map<String, Object> login(BaseLoginVO loginVO) {
        // 1 将用户密码进行加密（简单进行md5加密）
        loginVO.setPassword(SecureUtil.md5(loginVO.getPassword()));
        // 2 根据用户名密码进行搜库
        SysUserLogin loginUser = userLoginMapper.selectSingleByLoginName(loginVO.getLoginName());
        if (ObjectUtil.isNull(loginUser) || !S.isEq(loginUser.getPassword(), loginVO.getPassword())) {
            throw new BusinessException("用户名或密码错误，登录失败！");
        }
        // 3 生成登录成功秘钥, 并将其加入登录缓存
        String token = SecureUtil.md5(loginUser.getId() + loginUser.getLoginName());
        UserCache.LOGIN_USER.put(token, new ExpireData<SysUserLogin>(loginUser, UserCache.DEFAULT_USER_EXPIRE_TIME));
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("token", token);
        return retMap;
    }
    /**
     * 用户注销
     * @Author liuxb
     * @param: token
     * @Return void
     * @Date 2024/1/6
     **/
    @Override
    public void logout(String token) {
        if (S.isBlank(token)) {
            throw new BusinessException("当前用户未登录，无需注销！");
        }
        ExpireData<SysUserLogin> loginExpireData = UserCache.LOGIN_USER.get(token);
        if (ObjectUtil.isNull(loginExpireData)) {
            throw new BusinessException("当前用户未登录，无需注销！");
        }
        UserCache.LOGIN_USER.remove(token);
    }
}
