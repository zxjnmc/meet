package com.szx.meet.thread;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author szx
 * @Date 2021/3/27 20:27
 * @Description
 */
public class ThreadLocalRequest {
    /**
     * accessToken
     */
    private static final ThreadLocal<String> ACCESS_TOKEN = new ThreadLocal<>();

    private static final ThreadLocal<Integer> USER_ID = ThreadLocal.withInitial(() -> 0);

    private static final ThreadLocal<String> PHONE = ThreadLocal.withInitial(() -> "");

    private static final ThreadLocal<JSONObject> USER = new ThreadLocal<>();


    private ThreadLocalRequest() {
    }

    public static void removeAll() {
        ACCESS_TOKEN.remove();
        USER_ID.remove();
        PHONE.remove();
        USER.remove();
    }

    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN.set(accessToken);
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN.get();
    }

    public static void setUserId(Integer userId) {
        USER_ID.set(userId);
    }

    public static Integer getUserId() {
        return USER_ID.get();
    }

    public static void setPhone(String phone) {
        PHONE.set(phone);
    }

    public static String getPhone() {
        return PHONE.get();
    }

    public static void setUser(JSONObject user) {
        USER.set(user);
    }

    public static JSONObject getUser() {
        return USER.get();
    }
}
