package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface MainController {
	
	// 메인 로그인 페이지 접속
	public ModelAndView ArgusloginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	public ModelAndView main3(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 메인 페이지 접속
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public ModelAndView fronttest(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 로그인 페이지 접속
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 로그인
	public ModelAndView login(@RequestParam("login_id") String login_id, @RequestParam("login_pwd") String login_pwd,
			@RequestParam(value = "rememberId", required = false) String rememberId,
			RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 마이페이지
	public ModelAndView mypage(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 마이페이지 수정 폼
	public ModelAndView modMyPageForm(HttpServletRequest request) throws Exception;
	
	// 마이페이지 수정
	public ModelAndView modMyPage(@ModelAttribute("modMyPage") LoginVO modMyPage, HttpServletRequest request) throws Exception;
	
	// 비밀번호 수정
	public ModelAndView modPasswordForm(HttpServletRequest request) throws Exception;
	
	// 비밀번호 수정 (정보 저장)
	public ModelAndView modPassword(@RequestParam("changePassword") String changePassword, HttpServletRequest request) throws Exception;

	// 로그아웃
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception;

}

