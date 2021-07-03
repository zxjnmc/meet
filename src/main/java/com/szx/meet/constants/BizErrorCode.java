package com.szx.meet.constants;

/**
 * @Author szx
 * @Date 2021/3/27 17:53
 * @Description
 */
public enum BizErrorCode implements IErrorCode {

    OK(0, "success"),

    SERVER_STOPPING(33, "sever stopping"),

    UNDEFINED_ERROR(101, "未定义的错误"),

    ACCESS_TOKEN_ERROR(102, "accessToken错误"),

    ACCOUNT_ERROR(103, "用户名或密码错误,请重试！"),

    ACCOUNT_NOT_EXIST(104, "该手机号未注册"),

    SIGN_ERROR(105, "签名错误"),

    AUTHORITY_ERROR(106, "权限错误"),

    USER_IS_DISABLED(107, "当前账号已暂停使用,详情请联系客服咨询"),

    OPTIMISTIC_LOCK(201, "数据已被更新,请重新操作"),

    RESUBMIT(202, "请勿重复提交"),

    DUPLICATE_KEY_ERROR(203, "信息已经存在,请不要重复添加"),

    FEIGN_SERVICE_ERROR(301, "远程服务异常"),

    MS_CODE_ERROR(401, "验证码错误,请检查"),

    MS_CODE_USED(402, "验证码已使用,请重新获取"),

    MS_CODE_OUT_OF_TIME(403, "验证码已过期,请重新获取"),

    MS_CODE_SEND_ERROR(405, "验证码发送异常,请重试"),

    MS_CODE_SAVE_ERROR(406, "验证码信息保存异常"),

    MOBILE_NUMBER_ILLEGAL(407, "请输入正确的手机号"),

    BUSINESS_LIMIT_CONTROL_MINUTE(408, "获取验证码过于频繁,请等待1分钟后再试"),

    BUSINESS_LIMIT_CONTROL_HOUR(409, "获取验证码过于频繁,请等待1小时后再试"),

    BUSINESS_LIMIT_CONTROL_DAY(410, "获取验证码过于频繁,请等待24小时后再试"),

    BLACK_KEY_CONTROL_LIMIT(411, "黑名单管控"),

    MS_CODE_VERIFY(412, "验证码已校验，请重新获取"),
    ;

    private BizErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;

    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
