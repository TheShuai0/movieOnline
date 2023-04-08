package com.example.movieonlinedemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

@Configuration
public class LoginConfig implements WebMvcConfigurer {

    @Resource
    private LoginInterceptor loginInterceptor;

    //不拦截的请求
    String[] excludePatterns = {
            "/local/login",
            "/local/register"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("got there");
        // 添加拦截器
        registry.addInterceptor(loginInterceptor)
                // 要拦截的请求（所有请求）
                .addPathPatterns("/**")
                // 排除的拦截请求
                .excludePathPatterns(excludePatterns)
                .excludePathPatterns("/error");
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resource/**").addResourceLocations("classpath:/resource/");
    }



}
