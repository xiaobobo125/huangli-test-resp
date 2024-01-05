package com.huangli.web.vo;

import cn.hutool.http.HttpStatus;

import java.util.HashMap;
import java.util.List;

/**
 * 基础响应类
 * @Author liuxb
 * @Date 2024/1/5
 **/
public class BaseResponse extends HashMap{
    private final static String NODE_CODE = "code";
    private final static String NODE_MSG = "msg";
    private final static String NODE_CONTENT = "data";
    private final static String NODE_TOTAL = "total";

    private final static String DEFAULT_SUCCESS_MSG = "请求成功";
    private final static String DEFAULT_ERROR_MSG = "请求失败";

    public BaseResponse(){
        this(HttpStatus.HTTP_OK, DEFAULT_SUCCESS_MSG, null);

    }

    public BaseResponse(Integer code, String msg, Object content) {
        super.put(NODE_CODE, code);
        super.put(NODE_MSG, msg);
        super.put(NODE_CONTENT, content);
    }

    public BaseResponse(Integer code, Object content) {
        this(code, DEFAULT_SUCCESS_MSG, content);
    }

    public BaseResponse(Integer code, String msg) {
        this(code, msg, null);
    }

    public BaseResponse(Object content){
        this(HttpStatus.HTTP_OK, DEFAULT_SUCCESS_MSG, content);
    }

    /**
     * 成功返回
     * @Author liuxb
     * @param:
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/9/13
     **/
    public static BaseResponse success() {
        return new BaseResponse();
    }

    /**
     * 成功返回
     * @Author liuxb
     * @param:
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/9/13
     **/
    public static BaseResponse success(Object content) {
        return new BaseResponse(content);
    }

    /**
     * 失败空返回
     * @Author liuxb
     * @param:
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/9/13
     **/
    public static BaseResponse error() {
        return new BaseResponse(HttpStatus.HTTP_INTERNAL_ERROR, DEFAULT_ERROR_MSG);

    }
    /**
     * 失败带消息
     * @Author liuxb
     * @param:
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/9/13
     **/
    public static BaseResponse error(String msg) {
        return new BaseResponse(HttpStatus.HTTP_INTERNAL_ERROR, msg);
    }

    /**
     * 失败带消息带状态码，默认500
     * @Author liuxb
     * @param:
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/9/13
     **/
    public static BaseResponse error(Integer code, String msg) {
        return new BaseResponse(code, msg);
    }

    /**
     * 分页情况下设置总数和数据
     * @Author liuxb
     * @param: data
     * @param: total
     * @Return void
     * @Date 2024/1/5
     **/
    public void setDataAndTotal(List data, Long total) {
        super.put(NODE_CONTENT, data);
        super.put(NODE_TOTAL, total);
    }
}
