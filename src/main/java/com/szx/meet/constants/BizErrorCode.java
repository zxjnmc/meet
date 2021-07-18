package com.szx.meet.constants;

/**
 * @Author szx
 * @Date 2021/3/27 17:53
 * @Description
 */
public enum BizErrorCode implements IErrorCode {

    OK(200, "success"),

    SERVER_STOPPING(33, "sever stopping"),

    UNDEFINED_ERROR(101, "未定义的错误"),

    ACCESS_TOKEN_ERROR(102, "accessToken错误"),

    ACCOUNT_ERROR(103, "用户名或密码错误,请重试！"),

    ACCOUNT_NOT_EXIST(104, "该手机号未注册"),

    SIGN_ERROR(105, "签名错误"),

    AUTHORITY_ERROR(106, "权限错误"),

    USER_IS_DISABLED(107, "当前账号已暂停使用,详情请联系客服咨询"),

    LOGIN_CHANNEL_ERROR(108, "登录渠道方式错误"),

    OPTIMISTIC_LOCK(201, "数据已被更新,请重新操作"),

    RESUBMIT(202, "请勿重复提交"),

    DUPLICATE_KEY_ERROR(203, "信息已经存在,请不要重复添加"),

    FEIGN_SERVICE_ERROR(301, "远程服务异常"),

    VERIFY_CODE_ERROR(401, "验证码错误,请检查"),

    MS_CODE_USED(402, "验证码已使用,请重新获取"),

    VERIFY_CODE_NOT_EXISTS(403, "验证码已过期或不存在,请重新获取"),

    MS_CODE_SEND_ERROR(405, "验证码发送异常,请重试"),

    MS_CODE_SAVE_ERROR(406, "验证码信息保存异常"),

    PHONE_ILLEGAL(407, "请输入正确的手机号"),

    PHONE_NOT_NULL(408, "手机号不能为空"),

    BUSINESS_LIMIT_CONTROL_HOUR(409, "获取验证码过于频繁,请等待1小时后再试"),

    BUSINESS_LIMIT_CONTROL_DAY(410, "获取验证码过于频繁,请等待24小时后再试"),

    USER_NOT_EXISTS(411, "用户不存在"),
    PASSWORD_ERROR(412, "密码错误"),
    PASSWORD_SAME(413, "新旧密码相同"),
    PARAM_ERROR(414, "参数校验失败"),
    PERSON_TAG_NOT_EXISTS(415, "个人标签不存在"),
    PERSON_TAG_EXISTS(416, "个人标签已存在"),
    PARENT_TAG_NOT_LINK_USER(417, "父级标签不可关联到用户"),
    CAN_NOT_LIKE_YOUSELFT(418, "不能喜欢自己"),
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
