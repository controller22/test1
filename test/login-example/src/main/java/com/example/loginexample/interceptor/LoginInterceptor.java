package com.example.loginexample.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // 세션에서 로그인 정보를 가져옵니다.
        HttpSession session = request.getSession();
        Object principal = session.getAttribute("principal");

        // 로그인 정보가 없다면 로그인 페이지로 리다이렉트합니다.
        if (principal == null) {
            response.sendRedirect("/loginForm");
            return false;
        }

        // 로그인 정보가 있다면 계속 진행합니다.
        return true;
    }
}