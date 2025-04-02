package com.sboot.pro.argus.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.dao.LoginDAO;
import com.sboot.pro.argus.vo.LoginVO;

@Service("loginService")
@Transactional(propagation = Propagation.REQUIRED)
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO loginDAO;
	
	// 로그인
	@Override
	public LoginVO login(String login_id, String login_pwd) throws Exception {
		return loginDAO.login(login_id, login_pwd);
	}
}
