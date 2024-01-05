package com.huangli.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangli.web.domain.SysUserLogin;
import org.apache.ibatis.annotations.Param;

/**
* @author lxb
* @description 针对表【sys_user_login(用户登录信息表)】的数据库操作Mapper
* @createDate 2024-01-05 17:57:28
* @Entity com.huangli.web.domain.SysUserLogin
*/
public interface SysUserLoginMapper extends BaseMapper<SysUserLogin> {

    /**
     * 根据用户名查询登录用户数据信息
     * @Author liuxb
     * @param: loginName 登录名
     * @Return com.huangli.web.domain.SysUserLogin 登录用户信息
     * @Date 2024/1/5
     **/
    SysUserLogin selectSingleByLoginName(@Param("loginName") String loginName);
}
