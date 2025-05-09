package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.vo.SowVO;

public interface SowService {
	// sow 게시판 접속
	public SowVO sowBoardList(String searchArea) throws Exception;

	// sow 일일 추가 (정보저장)
	public void sowAddDailyWorkLogList(String searchArea, List<SowVO> sowDailyWorkLogList, String work_date) throws Exception;
	
	// sow 일일 보기
	public List<SowVO> selectViewList(String searchArea, String work_date) throws Exception;
	
	public List<SowVO> selectDayNightOvertime(String searchArea, String work_date) throws Exception;
	// 직원 등록 (정보저장)
	public int sowAddEmployee(String searchArea, String emp_name, String emp_position) throws Exception;
	// 직원 목록
	public List<SowVO> selectEmployeeList(String searchArea) throws Exception;

	public CombinedSowDailyWorkLog getCombinedSowDailyWorkLog(String searchArea, String work_date) throws Exception;
}
