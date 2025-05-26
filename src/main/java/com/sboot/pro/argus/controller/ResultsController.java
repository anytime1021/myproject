package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ResultsController {
	// 게시판 접속
	public ModelAndView resultsBoard(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 실적 추가 폼
	public ModelAndView addResultsForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 실적 추가(정보저장)
	public ModelAndView addResults(@RequestParam(value = "fmonth_name", required = false) String[] fmonth_nameArray,
	@RequestParam(value = "fmonth_profits", required = false) String[] fmonth_profitsArray,
	@RequestParam(value = "results_dailyprofits", required = false) String[] results_dailyprofitsArray,
	@RequestParam(value = "note", required = false) String[] noteArray,
	@RequestParam String work_date,
	HttpServletRequest request) throws Exception;
	
	// 실적 보기
	public ModelAndView resultsView(@RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception;
}
