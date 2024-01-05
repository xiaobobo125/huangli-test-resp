package com.huangli.web.controller;

import cn.hutool.core.convert.Convert;
import com.huangli.web.utils.S;
import com.huangli.web.vo.PageInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lxb
 * @version 1.0
 * @title BaseController
 * @description
 * @create 2024/1/5 20:12
 */
public class BaseController {
    /**
     * 获取分页信息
     * @Author liuxb
     * @param: request
     * @Return com.huangli.web.vo.PageInfoVO
     * @Date 2024/1/5
     **/
    public PageInfoVO getPageInfo(HttpServletRequest request) {
        long limit = S.isBlank(request.getParameter("limit")) ? 20L : Convert.toLong(request.getParameter("limit"));
        long page = S.isBlank(request.getParameter("page")) ? 20L : Convert.toLong(request.getParameter("page"));
        return PageInfoVO.builder()
                .page((page - 1) * limit)
                .limit(limit)
                .build();
    }
}
