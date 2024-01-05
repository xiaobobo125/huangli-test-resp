package com.huangli.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.huangli.web.domain.BaseEntity;
import com.huangli.web.domain.EmployeeInfo;
import com.huangli.web.exception.BusinessException;
import com.huangli.web.mapper.EmployeeInfoMapper;
import com.huangli.web.service.EmployeeService;
import com.huangli.web.utils.BaseEntityUtils;
import com.huangli.web.utils.S;
import com.huangli.web.vo.BaseResponse;
import com.huangli.web.vo.EmpQryDataVO;
import com.huangli.web.vo.PageInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.scanner.Constant;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lxb
 * @version 1.0
 * @title EmployeeServiceImpl
 * @description 职工相关业务类
 * @create 2024/1/5 20:09
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Resource
    private EmployeeInfoMapper employeeInfoMapper;
    /**
     * 获取职工数据列表
     * @Author liuxb
     * @param: params
     * @param: pageInfo
     * @Return com.huangli.web.vo.BaseResponse
     * @Date 2024/1/5
     **/
    @Override
    public BaseResponse getEmpList(EmpQryDataVO params, PageInfoVO pageInfo) {
        BaseResponse response = BaseResponse.success();
        // 统计总数
        Long cnt = employeeInfoMapper.countByParams(params);
        if (cnt > 0) {
            // 查询数据列表
            List<EmployeeInfo> empList =  employeeInfoMapper.selectByParams(params, pageInfo);
            response.setDataAndTotal(empList, cnt);
        }else{
            response.setDataAndTotal(new ArrayList(), cnt);
        }
        return response;
    }

    /**
     * 编辑保存职工列表
     * @Author liuxb
     * @param: params
     * @Return void
     * @Date 2024/1/5
     **/
    @Override
    public void editEmpList(EmpQryDataVO params) {
        if (S.isEmpty(params.getId())) {
            EmployeeInfo data = new EmployeeInfo();
            BeanUtils.copyProperties(params, data);
            BaseEntityUtils.initCreateInfo(data);
            employeeInfoMapper.insert(data);
        }else{
            EmployeeInfo info = employeeInfoMapper.selectById(params.getId());
            if (ObjectUtil.isNull(info) || S.isEq(Constants.ZERO, info.getFgActive())) {
                throw new BusinessException("当前传入的参数id不存在或数据已失效，更新失败！");
            }
            BeanUtils.copyProperties(params, info);
            BaseEntityUtils.initUpdateInfo(info);
            employeeInfoMapper.updateById(info);
        }
    }
}
