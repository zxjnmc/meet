package com.szx.meet.exception;

import com.szx.meet.consts.IErrorCode;

/**
 * @Author szx
 * @Date 2021/3/27 20:23
 * @Description 业务异常类
 */
public class BizException extends RuntimeException {
    private int code = 101;

    public BizException() {

    }

    public BizException(String message) {
        super(message);
    }

    public BizException(int errorCode, String message) {
        super(message);
        this.code = errorCode;
    }

    public static BizException getInstance(String message) {
        return new BizException(message);
    }

    public static BizException getInstance(String message, Object... args) {
        for (Object obj : args) {
            if (obj == null) {
                continue;
            }
            message = message.replaceFirst("\\{\\}", String.valueOf(obj));
        }
        return new BizException(message);
    }

    public BizException(IErrorCode errorCode) {
        this(errorCode, errorCode.getMessage());
    }

    public BizException(IErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public IErrorCode getErrorCode() {
        String message = this.getMessage();
        return new IErrorCode() {
            @Override
            public int getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return message;
            }
        };
    }

}
