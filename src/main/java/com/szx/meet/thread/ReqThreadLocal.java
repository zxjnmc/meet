package com.szx.meet.thread;

import com.alibaba.fastjson.JSONObject;
import com.szx.meet.consts.SystemConstants;

/**
 * @Author szx
 * @Date 2021/3/27 20:27
 * @Description
 */
public class ReqThreadLocal {
    /**
     * accessToken
     */
    private final static ThreadLocal<String> ACCESS_TOKEN = new ThreadLocal<>();

    /**
     * 请求用户的id
     */
    private final static ThreadLocal<Integer> USER_ID = ThreadLocal.withInitial(() -> 0);

    /**
     * 请求用户的名字
     */
    private final static ThreadLocal<String> USER_NAME = ThreadLocal.withInitial(() -> SystemConstants.DEFAULT_SYSTEM_OPERATOR);

    /**
     * 请求的ip地址
     */
    private final static ThreadLocal<String> IP = ThreadLocal.withInitial(() -> "unknown");

    /**
     * 请求的ip地址
     */
    private final static ThreadLocal<JSONObject> USER = new ThreadLocal<>();

    /**
     * 请求的query
     */
    private final static ThreadLocal<String> QUERY = new ThreadLocal<>();

    /**
     * 请求的body
     */
    private final static ThreadLocal<String> BODY = new ThreadLocal<>();

    /**
     * 链路id
     */
    private final static ThreadLocal<String> TID = new ThreadLocal<>();

    /**
     * 产品ID 0:ac 1:hm
     */
    private final static ThreadLocal<Integer> PID = new ThreadLocal<>();

    /**
     * 调试模式参数
     */
    private final static ThreadLocal<Integer> DEBUG = ThreadLocal.withInitial(() -> 0);

    public static void removeAll() {
        ACCESS_TOKEN.remove();
        USER_ID.remove();
        USER_NAME.remove();
        IP.remove();
        USER.remove();
        QUERY.remove();
        BODY.remove();
        TID.remove();
        PID.remove();
        DEBUG.remove();
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

    public static void setIp(String ip) {
        IP.set(ip);
    }

    public static String getIp() {
        return IP.get();
    }

    public static void setUserName(String userName) {
        USER_NAME.set(userName);
    }

    public static String getUserName() {
        return USER_NAME.get();
    }

    public static void setUser(JSONObject user) {
        USER.set(user);
    }

    public static JSONObject getUser() {
        return USER.get();
    }

    public static void setQuery(String query) {
        QUERY.set(query);
    }

    public static String getQuery() {
        return QUERY.get();
    }

    public static void setBody(String body) {
        BODY.set(body);
    }

    public static String getBody() {
        return BODY.get();
    }

    public static void setTid(String tid) {
        TID.set(tid);
    }

    public static String getTid() {
        return TID.get();
    }

    public static void setPid(Integer pid) {
        PID.set(pid);
    }

    public static Integer getPid() {
        return PID.get();
    }

    public static void setDebug(Integer debug) {
        DEBUG.set(debug);
    }

    public static Integer getDebug() {
        return DEBUG.get();
    }
}
