package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SowController {
	// sow 일일 게시판 접속
	public ModelAndView sowBoard(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 일일 추가 폼
	public ModelAndView sowAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 일일 추가 (정보저장)
	public ModelAndView sowAddDailyWorkLog(@RequestParam(value = "sowDWL_name", required=false) String[] sowDWL_nameArray,
			@RequestParam(value = "sowDWL_work_name", required=false) String[] sowDWL_work_nameArray,
			@RequestParam(value = "sowDWL_shift", required=false) String[] sowDWL_shiftArray,
			@RequestParam(value = "sowDWL_hours", required=false) String[] sowDWL_hoursArray,
			@RequestParam(value = "sowDWL_overtime", required=false) String[] sowDWL_overtimeArray,
			@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 일일 보기
	public ModelAndView sowView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// sow 일일 수정 폼
	public ModelAndView sowModDailyForm(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 월별 게시판 접속
	public ModelAndView sowBoardTotal(HttpServletRequest request, HttpServletResponse response) throws Exception;
	

	// 직원 목록
	public ModelAndView sowAddEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 직원 등록(정보저장)
	public ModelAndView sowAddEmployee(@RequestParam("sowMWL_name") String sowMWL_name, @RequestParam("searchDate") String searchDate, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
