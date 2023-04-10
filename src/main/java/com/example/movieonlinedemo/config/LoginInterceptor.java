package com.example.movieonlinedemo.config;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class LoginInterceptor implements HandlerInterceptor {


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }else {
            // 从请求头里面获取token,因为每次都会在请求头里面携带token
            System.out.println(request.getRequestURI());
            String token = request.getHeader("token");
            System.out.println("tokennnnnnnn:" + token);
            if (token != null && !"".equals(token)) {
                //根据head中的token，查询redis中是否有数据
                ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
                String tokenType = valueOperations.get("token");
                System.out.println(tokenType);
                // 如果能够获取到数据，说明token未过期
                if (tokenType != null || "".equals(tokenType)) {
                    valueOperations.set("token", tokenType, 30, TimeUnit.MINUTES);
                    valueOperations.set(tokenType, valueOperations.get(tokenType), 30, TimeUnit.MINUTES);
                    return true;
                }
            }
            //从请求头中获取不到token说明未登录，阻止该请求访问资源

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            Map<String, Object> message = new HashMap<>();
            message.put("msg", "用户登录信息已过期");
            message.put("code", "1101");
            out.write(new ObjectMapper().writeValueAsString(message));
            out.flush();
            out.close();
            return false;
        }
    }
}
