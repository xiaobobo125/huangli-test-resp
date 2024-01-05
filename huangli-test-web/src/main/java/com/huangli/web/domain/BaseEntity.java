package com.huangli.web.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author lxb
 * @version 1.0
 * @title BaseEntity
 * @description 实体基类
 * @create 2023/9/13 19:36
 */
@Data
public class BaseEntity {
    /**
     * 激活标志
     */
    private String fgActive;
    /**
     * 乐观锁
     */
    private String revision;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 更新时间
     */
    private Date updatedTime;
}
