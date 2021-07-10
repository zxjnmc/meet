package com.szx.meet.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author szx
 * @Date 2021/7/10 02:27
 * @Description
 */
public class HttpUtils {

    public static String getOs(String userAgent) {
        String os;
        String user = userAgent.toLowerCase();
        if (user.contains("windows")) {
            os = "Windows";
        } else if (user.contains("mac")) {
            os = "Mac";
        } else if (user.contains("android")) {
            os = userAgent.substring(user.indexOf("android")).split(";")[0];
        } else if (user.indexOf("iphone") >= 0) {
            os = "IPhone";
        } else if (user.contains("linux")) {
            os = "Linux";
        } else if (user.contains("x11")) {
            os = "Unix";
        } else {
            os = "UnKnown, More-Info: " + userAgent;
        }
        return os;
    }

    public static String getBrowser(String userAgent) {
        String browser = "";
        String user = userAgent.toLowerCase();
        if (user.contains("edge")) {
            browser = (userAgent.substring(user.indexOf("edge")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("ucbrowser") || user.contains("ubrowser")) {
            if (user.contains("ucbrowser")) {
                browser = (user.substring(user.indexOf("ucbrowser")).split(" ")[0]).replace("/", "-").replace("ucbrowser", "UC浏览器");
            } else if (user.contains("ubrowser")) {
                browser = (user.substring(user.indexOf("ubrowser")).split(" ")[0]).replace("/", "-").replace("ubrowser", "UC浏览器");
            }
        } else if (user.contains("msie")) {
            String substring = userAgent.substring(user.indexOf("msie")).split(";")[0];
            browser = substring.replace("MSIE", "IE").replace(" ", "-");
        } else if (user.contains("safari") && user.contains("version")) {
            browser = (userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if (user.contains("opr") || user.contains("opera")) {
            if (user.contains("opera")) {
                browser = (userAgent.substring(user.indexOf("opera")).split(" ")[0]).split("/")[0] + "-" + (userAgent.substring(user.indexOf("version")).split(" ")[0]).split("/")[1];
            } else if (user.contains("opr")) {
                browser = ((userAgent.substring(user.indexOf("opr")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
            }

        } else if (user.contains("chrome")) {
            browser = (userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("firefox")) {
            browser = (userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if (user.contains("rv")) {
            String IEVersion = (userAgent.substring(userAgent.indexOf("rv")).split(" ")[0]).replace("rv:", "-");
            browser = "IE" + IEVersion.substring(0, IEVersion.length() - 1);
        } else {
            browser = "UnKnown, More-Info: " + userAgent;
        }
        return browser;
    }

    public static String getIp(HttpServletRequest request) {
        //获取用户登录ip;
        String loginIp = request.getHeader("X-Forwarded-For");
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("X-Real-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (loginIp == null || loginIp.length() == 0 || "unknown".equalsIgnoreCase(loginIp)) {
            loginIp = request.getRemoteAddr();
        }
        return loginIp;
    }
}
