package com.szx.meet.exception;

/**
 * @Author szx
 * @Date 2021/3/27 20:55
 * @Description
 */
public class ParaException extends RuntimeException{

    private final static long serialVersionUID = 1L;

    public ParaException() {

    }

    public ParaException(String message) {
        super(message);
    }
}
