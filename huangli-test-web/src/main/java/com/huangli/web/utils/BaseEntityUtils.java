package com.huangli.web.utils;

import cn.hutool.core.date.DateUtil;
import com.huangli.web.domain.BaseEntity;

/**
 * 基类处理utils
 * @Author liuxb
 * @Date 2024/1/5
 **/
public class BaseEntityUtils {
    /**
     * 初始化实体类创建信息
     * @Author liuxb
     * @param: entity
     * @Return void
     * @Date 2023/9/14
     **/
    public static void initCreateInfo(BaseEntity entity) {
        entity.setCreatedBy("admin");
        entity.setCreatedTime(DateUtil.date());
        entity.setUpdatedBy("admin");
        entity.setUpdatedTime(DateUtil.date());
        entity.setRevision("0");
        entity.setFgActive("1");
    }
    /**
     * 初始化实体类更新信息
     * @Author liuxb
     * @param: entity
     * @Return void
     * @Date 2023/9/14
     **/
    public static void initUpdateInfo(BaseEntity entity) {
        entity.setUpdatedBy("admin");
        entity.setUpdatedTime(DateUtil.date());
        entity.setRevision("0");
    }
}
