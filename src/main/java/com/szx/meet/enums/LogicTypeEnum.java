package com.szx.meet.enums;

/**
 * @Author szx
 * @Date 2021/7/9 21:57
 * @Description
 */
public enum LogicTypeEnum implements BaseEnum {
    YES(1, "真"),
    NO(0, "假"),
    ;

    private Integer value;
    private String msg;


    LogicTypeEnum(Integer value, String msg) {
        this.value = value;
        this.msg = msg;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
