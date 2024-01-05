package com.huangli.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lxb
 * @version 1.0
 * @title PageInfoVO
 * @description
 * @create 2024/1/5 20:11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageInfoVO {
    /**
     * 分页页数
     **/
    private Long page;
    /**
     * 每页条数
     **/
    private Long limit;
}
