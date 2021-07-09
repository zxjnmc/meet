package com.szx.meet.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szx.meet.thread.ThreadLocalRequest;
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
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            handleToken(request);
            filterChain.doFilter(request, response);
        } finally {
            ThreadLocalRequest.removeAll();
        }
    }

    private void handleToken(HttpServletRequest request) {
        // 获取请求头
        String accessToken = request.getHeader("access-token");
        if (!StringUtils.hasText(accessToken)) {
            return;
        }
        Object operator = redisUtils.getValue(accessToken);
        if (operator != null) {
            JSONObject userJson = (JSONObject) JSON.toJSON(operator);
            int userId = userJson.getInteger("userId");
            String phone = userJson.getString("phone");
            ThreadLocalRequest.setAccessToken(accessToken);
            ThreadLocalRequest.setUserId(userId);
            ThreadLocalRequest.setPhone(phone);
            ThreadLocalRequest.setUser(userJson);
        }
    }
}
