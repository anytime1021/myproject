package com.sboot.pro.argus.controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportController {
	// 일별 보고서 게시판 접속
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일별 보고서 보기
	public ModelAndView reportView(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기 양식
	public ModelAndView addDailyReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public ModelAndView addReport(@RequestParam(value = "work_name", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTStr,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTStr,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDStr,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTStr,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTStr,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 수정 폼
	public ModelAndView modDailyReport(@RequestParam("board_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 수정
	public ModelAndView modReport(@RequestParam(value = "work_name", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTArray,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTArray,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDArray,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTArray,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTArray,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam("work_date") String work_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 삭제
	public ModelAndView removeDailyReport(@RequestParam("board_date") String work_dat, HttpServletResponse response, HttpServletRequest request) throws Exception;
	
	// 월별 보고서 게시판 접속
	public ModelAndView reportAreaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 전체 양식
	public ModelAndView addTotalReportForm(@RequestParam("work_date_total") String work_date_total, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 현장 추가
	public ModelAndView addTotalReport(@ModelAttribute("addTotalReport") ReportVO addTotal, HttpServletRequest request, HttpServletResponse response) throws Exception;
//	public ModelAndView addTotalReport(@ModelAttribute("addTotal") ReportVO addTotal, @RequestParam(value = "work_date_total", required = false) String workDateStr, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 수정 폼
	public ModelAndView modTotalReportForm(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 수정
	public ModelAndView modTotalReport(@RequestParam(value = "work_name_total", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_RT_total", required = false) String[] work_amount_RTArray,
			@RequestParam(value = "work_amount_PAUT_total", required = false) String[] work_amount_PAUTArray,
			@RequestParam(value = "work_amount_TOFD_total", required = false) String[] work_amount_TOFDArray,
			@RequestParam(value = "work_amount_UT_total", required = false) String[] work_amount_UTArray,
			@RequestParam(value = "work_amount_MPT_total", required = false) String[] work_amount_MPTArray,
			@RequestParam(value = "work_manpower_total", required = false) String[] work_manpowerArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			@RequestParam("work_date") String work_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 월별 보고서 행 삭제
	public ReportVO removeTotalReportRow(@RequestParam("work_name_total") String work_name_total, @RequestParam("work_date_total") String work_date_total,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// -------------------------------------------------------------------------------------------------------------------
	
	// sow 게시판 접속
	public ModelAndView sowBoard(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 추가 폼
	public ModelAndView sowAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 월별 게시판 접속
	public ModelAndView sowBoardTotal(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
