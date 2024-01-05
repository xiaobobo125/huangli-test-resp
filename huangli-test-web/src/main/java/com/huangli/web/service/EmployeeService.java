package com.huangli.web.service;

import com.huangli.web.vo.BaseResponse;
import com.huangli.web.vo.EmpQryDataVO;
import com.huangli.web.vo.PageInfoVO;

/**
 * @author lxb
 * @version 1.0
 * @title EmplyeeService
 * @description 职工相关业务接口
 * @create 2024/1/5 20:09
 */
public interface EmployeeService {
    /**
     * 获取职工数据列表
     * @Author liuxb
     * @param: params
     * @param: pageInfo
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    BaseResponse getEmpList(EmpQryDataVO params, PageInfoVO pageInfo);

    /**
     * 编辑保存职工列表
     * @Author liuxb
     * @param: params
     * @Return void
     * @Date 2024/1/5
     **/
    void editEmpList(EmpQryDataVO params);
}
