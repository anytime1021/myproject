package com.sboot.pro.argus.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportController {
	// 보고서 게시판 접속
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일별 보고서 보기
	public ModelAndView reportView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 전체 양식
	public ModelAndView totalReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 현장 추가
	public ModelAndView addTotalReport(@ModelAttribute("addTotal") ReportVO addTotal, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기 양식
	public ModelAndView addReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 보고서 글쓰기(정보저장)
	public ModelAndView addReport(@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTStr,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTStr,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDStr,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTStr,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTStr,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam(value = "work_xray", required = false) String[] work_xrayArray,
			@RequestParam(value = "work_PAUT", required = false) String[] work_PAUTArray,
			@RequestParam(value = "work_charyang", required = false) String[] work_charyangArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
}
