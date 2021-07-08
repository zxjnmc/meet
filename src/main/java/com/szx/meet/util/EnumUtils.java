package com.szx.meet.util;

import com.szx.meet.enums.BaseEnum;

/**
 * @Author szx
 * @Date 2021/7/4 11:44
 * @Description
 */
public class EnumUtils {

    public static <T extends BaseEnum> String getByCode(Integer value, Class<T> t) {
        for (T item : t.getEnumConstants()) {
            if (item.getValue().equals(value)) {
                return item.getMsg();
            }
        }
        return "";
    }
}
