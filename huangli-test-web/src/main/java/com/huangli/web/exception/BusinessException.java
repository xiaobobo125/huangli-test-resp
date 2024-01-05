package com.huangli.web.exception;

/**
 * 业务异常，方便全局异常处理
 * @Author liuxb
 * @Date 2024/1/5
 **/
public class BusinessException extends RuntimeException{
    private String message;
    private String detailMessage;

    public BusinessException() {
    }

    public BusinessException(String message) {
        this.message = message;
    }

    public String getDetailMessage() {
        return this.detailMessage;
    }

    public BusinessException setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
        return this;
    }
    @Override
    public String getMessage() {
        return this.message;
    }

    public BusinessException setMessage(String message) {
        this.message = message;
        return this;
    }
}
