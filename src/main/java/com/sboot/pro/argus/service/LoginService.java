package com.sboot.pro.argus.service;

import com.sboot.pro.argus.vo.LoginVO;

public interface LoginService {
	public LoginVO login(String login_id, String login_pwd) throws Exception;
}
