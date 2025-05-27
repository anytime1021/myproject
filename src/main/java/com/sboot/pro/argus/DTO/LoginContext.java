package com.sboot.pro.argus.DTO;

import com.sboot.pro.argus.vo.LoginVO;

public class LoginContext {

    private static final ThreadLocal<LoginVO> loginThreadLocal = new ThreadLocal<>();

    public static void setLogin(LoginVO login) {
        loginThreadLocal.set(login);
    }

    public static LoginVO getLogin() {
        return loginThreadLocal.get();
    }

    public static void clear() {
        loginThreadLocal.remove();
    }
}

