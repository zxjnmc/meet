package com.szx.meet.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szx.meet.constants.RedisKeyConstants;
import com.szx.meet.thread.ReqThreadLocal;
import com.szx.meet.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Author szx
 * @Date 2021/3/28 15:26
 * @Description
 */
@Component
public class ThreadLocalFilter extends OncePerRequestFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            handleToken(request);
            filterChain.doFilter(request, response);
        } catch (Throwable e) {
            throw e;
        } finally {
            ReqThreadLocal.removeAll();
        }
    }

    /**
     * 解析请求头token，并设置到本地线程变量
     *
     * @param request
     */
    private void handleToken(HttpServletRequest request) {
        // 获取请求头
        String accessToken = request.getHeader("access-token");
        if (!StringUtils.hasText(accessToken)) {
            return;
        }
        Object userObj = redisUtils.getValue(String.format(RedisKeyConstants.ACCESS_TOKEN, accessToken));
        if (!Objects.isNull(userObj)) {
            JSONObject user = (JSONObject) JSON.toJSON(userObj);
            int userId = user.getIntValue("id");
            String userName = user.getString("cnName");
            ReqThreadLocal.setAccessToken(accessToken);
            ReqThreadLocal.setUserId(userId);
            ReqThreadLocal.setUserName(userName);
            ReqThreadLocal.setUser(user);
        }
    }
}
