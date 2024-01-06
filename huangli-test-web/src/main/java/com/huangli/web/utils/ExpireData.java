package com.huangli.web.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.huangli.web.exception.BusinessException;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author lxb
 * @version 1.0
 * @title ExpireData
 * @description
 * @create 2023/01/05
 */
public class ExpireData<T> {

    /**
     * 存在时限的值
     **/
    private T value;

    /**
     * 过期时间
     **/
    private Long expireTime;

    /**
     * 过期单位
     **/
    private DateUnit timeUnit;

    /**
     * 值保存时间
     **/
    private Date valueDate;

    public ExpireData(T value, Long expireTime, DateUnit timeUnit) {
        this.value = value;
        this.expireTime = expireTime;
        this.timeUnit = timeUnit;
        this.valueDate = new Date();
    }

    public ExpireData(T value, Long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
        this.timeUnit = DateUnit.SECOND;
        this.valueDate = new Date();
    }

    public ExpireData(T value) {
        this.value = value;
    }

    /**
     * 获取值
     * @Author liuxb
     * @param:
     * @Return java.lang.Object
     * @Date 2023/01/05
     **/
    public T getValue() {
        // 若存在过期时间，则判断当前参数是否已经过期
        if (ObjectUtil.isNotNull(expireTime)) {
            long date = DateUtil.between(DateUtil.date(), valueDate, timeUnit);
            if (date >= expireTime) {
                return null;
            }
        }
        return value;
    }
}
