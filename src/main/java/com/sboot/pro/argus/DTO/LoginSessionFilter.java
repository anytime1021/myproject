package com.sboot.pro.argus.DTO;

import java.io.IOException;

import com.sboot.pro.argus.DTO.LoginContext;

import org.springframework.stereotype.Component;

import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);

        if (session != null) {
            LoginVO login = (LoginVO) session.getAttribute("login");
            if (login != null) {
                req.setAttribute("login", login);
                LoginContext.setLogin(login);
            }
        }

        try {
            chain.doFilter(request, response);
        } finally {
            LoginContext.clear();
        }
    }
}

