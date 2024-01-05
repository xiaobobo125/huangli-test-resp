package com.huangli.web.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 职工信息表
 * @TableName employee_info
 */
@Data
@TableName(value ="employee_info")
public class EmployeeInfo extends BaseEntity implements Serializable {
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 职员姓名
     */
    private String empName;

    /**
     * 性别【0，未知，1男，2女】
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 部门名称【可选范围[1业务部，2后勤部，3人事部]】
     */
    private String deptName;

    /**
     * 学历【可选范围[1大专，2本科，3研究生]】
     */
    private String empDegreeName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}