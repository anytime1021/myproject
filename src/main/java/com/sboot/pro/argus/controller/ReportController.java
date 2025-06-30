package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportController {
	// 일일 보고서 게시판 접속
	public ModelAndView reportArea(@RequestParam(value="page", defaultValue = "1") int page, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기(게시판)
	public ModelAndView addDailyReportBoard(@RequestParam("work_date") String work_date, @RequestParam("board_title") String board_title,
			HttpServletRequest request, HttpServletResponse response) throws Exception;


	///////////////////////////////////////////////////////////////////////////////////////////////

}
