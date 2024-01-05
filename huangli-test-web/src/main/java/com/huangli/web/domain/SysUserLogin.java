package com.huangli.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录信息表
 * @TableName sys_user_login
 */
@Data
@TableName(value ="sys_user_login")
public class SysUserLogin extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 密码
     */
    private String password;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 激活标志
     */
    private String fgActive;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}