package com.huangli.web.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author lxb
 * @version 1.0
 * @title EmpQryVO
 * @description
 * @create 2024/1/5 20:06
 */
@ApiModel("职员信息")
@Data
public class EmpQryDataVO {
    private String id;
    /**
     * 职员姓名
     */
    @ApiModelProperty("职员姓名")
    @NotBlank(message = "职员姓名不能为空！")
    private String empName;

    /**
     * 性别【0，未知，1男，2女】
     */
    @ApiModelProperty("性别【0，未知，1男，2女】")
    @NotBlank(message = "性别不能为空！")
    private String sex;

    /**
     * 年龄
     */
    @ApiModelProperty("年龄")
    @NotNull(message = "年龄不能为空！")
    private Integer age;

    /**
     * 部门名称【可选范围[1业务部，2后勤部，3人事部]】
     */
    @ApiModelProperty("部门名称【可选范围[1业务部，2后勤部，3人事部]】")
    @NotBlank(message = "部门名称不能为空！")
    private String deptName;

    /**
     * 学历【可选范围[1大专，2本科，3研究生]】
     */
    @ApiModelProperty("学历【可选范围[1大专，2本科，3研究生]】")
    @NotBlank(message = "学历不能为空！")
    private String empDegreeName;
    /**
     * 激活标志
     */
    @ApiModelProperty("激活标志")
    private String fgActive;
}
