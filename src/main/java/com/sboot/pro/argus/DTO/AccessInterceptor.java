package com.sboot.pro.argus.DTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sboot.pro.argus.vo.LoginVO;

public class AccessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("login") == null) {
            response.sendRedirect(request.getContextPath() + "/argus/loginForm.do");
            return false;
        }

        LoginVO login = (LoginVO) session.getAttribute("login");
        String uri = request.getRequestURI();

        // 보고서 게시판 권한 확인
        if (uri.startsWith(request.getContextPath() + "/report")
                && login.getLogin_report_access() != 1) {
            response.sendRedirect(request.getContextPath() + "/argus/noAuth.do");
            return false;
        }

        // 시험편 관리 권한 확인
        if (uri.startsWith(request.getContextPath() + "/blockManagement")
                && login.getLogin_block_access() != 1) {
            response.sendRedirect(request.getContextPath() + "/argus/noAuth.do");
            return false;
        }

        return true;
    }
}

