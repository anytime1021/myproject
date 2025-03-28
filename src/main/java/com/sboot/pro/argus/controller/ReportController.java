package com.sboot.pro.argus.controller;

import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportController {
	// 보고서 게시판 접속
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 보고서 글쓰기 양식
	public ModelAndView addReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
