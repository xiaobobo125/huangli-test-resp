package com.huangli.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author lxb
 * @version 1.0
 * @title BaseLoginVO
 * @description
 * @create 2024/1/5 17:49
 */
@ApiModel("用户登录实体")
@Data
public class BaseLoginVO {
    /**
     * 登录用户名
     **/
    @ApiModelProperty("登录用户名")
    @NotBlank(message = "登录名不能为空！")
    private String loginName;
    /**
     * 登录密码：明文
     **/
    @ApiModelProperty("登录密码")
    @NotBlank(message = "登录密码不能为空！")
    private String password;
}
