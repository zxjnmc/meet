package com.szx.meet.exception;

import com.szx.meet.constants.IErrorCode;

/**
 * @Author szx
 * @Date 2021/3/27 20:23
 * @Description 业务异常类
 */
public class BizException extends RuntimeException {
    private int code = 200;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }

    public BizException(int code, String message) {
        super(message);
        this.code = code;
    }

    public static BizException getInstance(String message) {
        return new BizException(message);
    }

    public BizException(IErrorCode code) {
        this(code, code.getMessage());
    }

    public BizException(IErrorCode code, String message) {
        super(message);
        this.code = code.getCode();
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
