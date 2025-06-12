package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;

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
			@RequestParam(value = "emp_num", required=false) String[] emp_numArray,
			@RequestParam(value = "sowDWL_name_in", required=false) String[] sowDWL_name_inArray,
			@RequestParam(value = "sowDWL_work_name_in", required=false) String[] sowDWL_work_name_inArray,
			@RequestParam(value = "sowDWL_shift_in", required=false) String[] sowDWL_shift_inArray,
			@RequestParam(value = "sowDWL_hours_in", required=false) String[] sowDWL_hours_inArray,
			@RequestParam(value = "sowDWL_overtime_in", required=false) String[] sowDWL_overtime_inArray,
			@RequestParam(value = "emp_num_in", required=false) String[] emp_num_inArray,
			@RequestParam(value = "sowDWL_name_out", required=false) String[] sowDWL_name_outArray,
			@RequestParam(value = "sowDWL_work_name_out", required=false) String[] sowDWL_work_name_outArray,
			@RequestParam(value = "sowDWL_shift_out", required=false) String[] sowDWL_shift_outArray,
			@RequestParam(value = "sowDWL_hours_out", required=false) String[] sowDWL_hours_outArray,
			@RequestParam(value = "sowDWL_overtime_out", required=false) String[] sowDWL_overtime_outArray,
			@RequestParam(value = "emp_num_out", required=false) String[] emp_num_outArray,
			@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// sow 월별 게시판 접속
	public ModelAndView sowBoardTotal(HttpServletRequest request, HttpServletResponse response) throws Exception;
	

	// 직원 목록
	public ModelAndView sowAddEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 직원 등록(정보저장)
	public ModelAndView sowAddEmployee(@RequestParam("sowMWL_name") String sowMWL_name, @RequestParam("searchDate") String searchDate, HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 출장자 추가 폼
	public ModelAndView sowAddBtEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 출장자 추가(정보저장)
	public ModelAndView sowAddBtEmployee(@RequestParam("emp_name") String emp_name, @RequestParam("sowDWL_work_name") String sowDWL_work_name, 
			@RequestParam("bt_inout") String bt_inout, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
