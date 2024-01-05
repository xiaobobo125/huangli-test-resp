package com.huangli.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huangli.web.domain.EmployeeInfo;
import com.huangli.web.vo.EmpQryDataVO;
import com.huangli.web.vo.PageInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author lxb
* @description 针对表【employee_info(职工信息表)】的数据库操作Mapper
* @createDate 2024-01-05 17:57:28
* @Entity com.huangli.web.domain.EmployeeInfo
*/
public interface EmployeeInfoMapper extends BaseMapper<EmployeeInfo> {

    /**
     * 统计职工总数
     * @Author liuxb
     * @param: params
     * @Return java.lang.Long
     * @Date 2024/1/5
     **/
    Long countByParams(@Param("param") EmpQryDataVO params);

    /**
     * 查询数据
     * @Author liuxb
     * @param: params
     * @param: pageInfo
     * @Return java.util.List<com.huangli.web.domain.EmployeeInfo>
     * @Date 2024/1/5
     **/
    List<EmployeeInfo> selectByParams(@Param("param") EmpQryDataVO params,
                                      @Param("page") PageInfoVO pageInfo);
}
