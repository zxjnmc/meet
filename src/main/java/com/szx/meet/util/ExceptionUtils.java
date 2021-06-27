package com.szx.meet.util;

/**
 * @Author szx
 * @Date 2021/3/28 15:58
 * @Description
 */
public class ExceptionUtils {

    public static String getExceptionMsg(Throwable cause) {
        StringBuilder buffer = new StringBuilder();
        buffer.append("\n").append(cause.toString()).append("\n");
        for (int i = 0; i < cause.getStackTrace().length; i++) {
            buffer.append("    ").append(cause.getStackTrace()[i].toString()).append("\n");
        }
        return buffer.toString();
    }
}
