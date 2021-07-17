package com.szx.meet.enums;

/**
 * @Author szx
 * @Date 2021/7/17 19:19
 * @Description 用户关系类型枚举
 */
public enum UserRelationShipTypeEnum implements BaseEnum {
    //1-喜欢 2-超级喜欢 3-无感 4-拉黑
    LIKE(1, "喜欢"),
    SUPER_LIKE(2, "超级喜欢"),
    NO_SENSE(3, "无感"),
    BLOCK(4, "拉黑"),

    ;

    UserRelationShipTypeEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    private Integer value;
    private String msg;

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
