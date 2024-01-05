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
import com.huangli.web.utils.S;
import com.huangli.web.vo.BaseLoginVO;
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
        // 3 生成登录成功秘钥
        Map<String, Object> retMap = new HashMap<>();
        // TODO：暂时生成虚假id，后续优化
        retMap.put("token", SecureUtil.simpleUUID());
        return retMap;
    }
}
