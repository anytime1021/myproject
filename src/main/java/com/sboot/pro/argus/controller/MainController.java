package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MainController {
	// 메인 페이지 접속
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView fronttest(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 로그인 페이지 접속
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 로그인
	public ModelAndView login(@RequestParam("login_id") String login_id, @RequestParam("login_pwd") String login_pwd,
			RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

}

