package com.szx.meet.response;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.szx.meet.constants.BizErrorCode;
import com.szx.meet.constants.IErrorCode;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author szx
 * @Date 2021/3/27 17:41
 * @Description Api返回类
 */
@Data
public class ApiResult<T> implements Serializable {
    private final static long serialVersionUID = 1L;
    /**
     * 返回值
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 返回结果
     */
    private T data;

    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date currentTime = new Date();
    /**
     * 当前时间戳
     */
    private Long currentTimestamp = System.currentTimeMillis();

    public static <T> ApiResult<T> success() {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(BizErrorCode.OK.getCode());
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = success();
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data, int total) {
        return success(data);
    }

    public static <T> ApiResult<T> fail() {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(BizErrorCode.UNDEFINED_ERROR.getCode());
        apiResult.setMsg(BizErrorCode.UNDEFINED_ERROR.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> fail(String msg) {
        ApiResult<T> apiResult = fail();
        apiResult.setMsg(msg);
        return apiResult;
    }

    public static <T> ApiResult<T> fail(IErrorCode code) {
        ApiResult<T> apiResult = fail();
        apiResult.setCode(code.getCode());
        apiResult.setMsg(code.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> fail(IErrorCode code, String msg) {
        ApiResult<T> apiResult = fail();
        apiResult.setCode(code.getCode());
        apiResult.setMsg(msg);
        return apiResult;
    }

    public boolean isSuccess() {
        return BizErrorCode.OK.getCode() == code;
    }

    @Override
    public String toString() {
        return "Result [code = " + code + ", msg = " + msg + ", data = " + data + "]";
    }
}
