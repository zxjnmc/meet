package com.szx.meet.enums;

/**
 * @Author szx
 * @Date 2021/7/4 11:36
 * @Description 登录渠道类型
 */
public enum ChannelTypeEnum implements BaseEnum {

    PHONE_PWD(1, "手机号密码登录"),
    PHONE_CODE(2, "手机号验证码登录"),
    EMAIL_CODE(3, "邮箱验证码登录"),
    MINI_WECHAT(4, "微信小程序"),


    ;
    private final Integer value;
    private final String msg;

    ChannelTypeEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    public Integer getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }

}
