package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportController {
	// 일일 보고서 게시판 접속
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기 양식
	public ModelAndView addDailyReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기(게시판)
	public ModelAndView addDailyReportBoard(@RequestParam("work_date") String work_date, @RequestParam("board_title") String board_title,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public ModelAndView addReport(@RequestParam(value = "work_name", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTArray,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTArray,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDArray,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTArray,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTArray,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charayng_total", required = false) String[] work_charyang_totalArray,
			@RequestParam(value = "work_num_total", required = false) int[] work_num_total,
			@RequestParam("work_date") String work_date, @RequestParam("work_title") String work_title,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 일일 보고서 보기
	public ModelAndView reportView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 수정 폼
	public ModelAndView modDailyReport(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 수정(정보저장)
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
	public ModelAndView removeDailyReport(@RequestParam("work_date") String work_dat, HttpServletResponse response, HttpServletRequest request) throws Exception;

	// 월별 보고서 게시판 접속
	public ModelAndView reportAreaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	


	

	
	// -------------------------------------------------------------------------------------------------------------------
	


	///////////////////////////////////////////////////////////////////////////////////////////////
	// 2025-04-17 전체 수정 후
	
	// 작업현황 현장 추가 폼
	public ModelAndView addTotalReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 작업현황 현장 추가
	public ModelAndView addTotalReport(@RequestParam(value = "work_name_total", required = false) String[] work_name_totalArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 작업현황 현장 수정 폼
	public ModelAndView modTotalReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 작업현황 현장 수정(정보저장)
	public ModelAndView modTotalReport(@RequestParam(value = "work_num_total", required = false) String[] work_num_total,
			@RequestParam(value = "work_name_total", required = false) String[] work_name_totalArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 작업현황 행 삭제
	public ReportVO removeTotalReportRow(@RequestParam("work_num_total") int work_num_total,
			HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	

}
