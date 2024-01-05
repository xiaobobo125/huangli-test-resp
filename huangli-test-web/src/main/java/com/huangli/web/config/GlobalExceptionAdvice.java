package com.huangli.web.config;

import com.huangli.web.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * @Author liuxb
 * @Date 2024/1/5
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    /**
     * 全局异常处理
     *
     * @Author liuxb
     * @param: e
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/10/20
     **/
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生异常.", requestURI, e);
        return BaseResponse.error(e.getMessage());
    }

    /**
     * 全局异常处理
     *
     * @Author liuxb
     * @param: e
     * @Return com.bolife.common.vo.BaseResponse
     * @Date 2023/10/20
     **/
    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',发生系统异常.", requestURI, e);
        return BaseResponse.error(e.getMessage());
    }
}
