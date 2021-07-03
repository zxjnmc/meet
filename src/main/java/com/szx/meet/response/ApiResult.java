package com.szx.meet.response;

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
     * 错误码
     */
    private int errorCode;
    /**
     * 错误提示
     */
    private String errorMessage;
    /**
     * 数据
     */
    private T data;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 当前时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    private Date currentTime = new Date();
    /**
     * 当前时间戳
     */
    private Long currentTimestamp = System.currentTimeMillis();
    /**
     * 额外数据
     */
    //private Object attach;

    public static <T> ApiResult<T> success() {
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setErrorCode(BizErrorCode.OK.getCode());
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data) {
        ApiResult<T> apiResult = success();
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> success(T data, int total) {
        ApiResult<T> apiResult = success(data);
        apiResult.setTotal(total);
        return apiResult;
    }

    public static <T> ApiResult<T> fail() {
        ApiResult<T> apiResult = new ApiResult<T>();
        apiResult.setErrorCode(BizErrorCode.UNDEFINED_ERROR.getCode());
        apiResult.setErrorMessage(BizErrorCode.UNDEFINED_ERROR.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> fail(String errorMessag) {
        ApiResult<T> apiResult = fail();
        apiResult.setErrorMessage(errorMessag);
        return apiResult;
    }

    public static <T> ApiResult<T> fail(IErrorCode errorCode) {
        ApiResult<T> apiResult = fail();
        apiResult.setErrorCode(errorCode.getCode());
        apiResult.setErrorMessage(errorCode.getMessage());
        return apiResult;
    }

    public static <T> ApiResult<T> fail(IErrorCode errorCode, String description) {
        ApiResult<T> apiResult = fail();
        apiResult.setErrorCode(errorCode.getCode());
        apiResult.setErrorMessage(description);
        return apiResult;
    }

    public boolean isSuccess() {
        return BizErrorCode.OK.getCode() == errorCode;
    }

    /*@Override
    public String toString() {
        return "Result [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", data=" + data + ", total="
                + total + ", attach=" + attach + "]";
    }*/

    @Override
    public String toString() {
        return "Result [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", data=" + data + ", total="
                + total + "]";
    }
}
