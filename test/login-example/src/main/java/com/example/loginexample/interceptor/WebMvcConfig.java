package com.example.loginexample.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // LoginInterceptor를 등록합니다.
        registry.addInterceptor(new LoginInterceptor())
                // /secure/** 패턴에 대해서만 인터셉터를 적용합니다.
                .addPathPatterns("/");

    }
}