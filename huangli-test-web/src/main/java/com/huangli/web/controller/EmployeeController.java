package com.huangli.web.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.huangli.web.exception.BusinessException;
import com.huangli.web.service.EmployeeService;
import com.huangli.web.utils.S;
import com.huangli.web.vo.BaseResponse;
import com.huangli.web.vo.EmpQryDataVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author lxb
 * @version 1.0
 * @title EmployeeController
 * @description 职工控制controller
 * @create 2024/1/5 20:05
 */
@Api(tags = "职工信息")
@RestController
@RequestMapping("/emp")
public class EmployeeController extends BaseController{
    @Resource
    private EmployeeService employeeService;
    /**
     * 获取职工列表
     * @Author liuxb
     * @param: params
     * @param: request
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @ApiOperation("获取职工列表")
    @PostMapping("/list")
    public BaseResponse getEmpList(@RequestBody(required = false) EmpQryDataVO params, HttpServletRequest request) {
        if (ObjectUtil.isNull(params)) {
            params = new EmpQryDataVO();
        }
        return employeeService.getEmpList(params, getPageInfo(request));
    }
    /**
     * 编辑职工列表
     * @Author liuxb
     * @param: params
     * @param: request
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @ApiOperation("编辑保存职工列表")
    @PostMapping("/edit")
    public BaseResponse editEmpList(@Validated @RequestBody EmpQryDataVO params) {
        employeeService.editEmpList(params);
        return BaseResponse.success();
    }
    /**
     * 删除职工列表
     * @Author liuxb
     * @param: params
     * @param: request
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @ApiOperation("删除职工列表")
    @PostMapping("/delete/{id}")
    public BaseResponse deleteEmpList(@PathVariable("id") String id) {
        if (S.isEmpty(id)) {
            throw new BusinessException("当前删除数据id不能为空！");
        }
        EmpQryDataVO voParams = new EmpQryDataVO();
        voParams.setId(id);
        voParams.setFgActive(Constants.ZERO);
        employeeService.editEmpList(voParams);
        return BaseResponse.success();
    }
}
