package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.vo.SowVO;

public interface SowService {
	// sow 게시판 접속
	public SowVO sowBoardList(String searchArea) throws Exception;

	// sow 일일 추가 (정보저장)
	public void sowAddDailyWorkLogList(String searchArea, List<SowVO> sowDailyWorkLogList, String work_date) throws Exception;
	public void sowAddBusinessTrip(String searchArea, List<SowVO> sowBusinessTrip, String work_date) throws Exception;
	
	// sow 일일 보기
	public List<SowVO> selectViewList(String searchArea, String work_date) throws Exception;
	
	public List<SowVO> selectDayNightOvertime(String searchArea, String work_date) throws Exception;
	
	public List<SowVO> selectShiftType(String searchArea, String work_date) throws Exception;
	
	public List<SowVO> selectBusinessTrip(String searchArea, String work_date, String bt_inout) throws Exception;

	public int countBtViewList(String searchArea, String bt_inout, String work_date) throws Exception;
	
	// sow 일일 수정
	public int sowModDailyWorkLogList(List<SowVO> sowDailyWorkLogList, String searchArea, String login_id, String work_date) throws Exception;

	// 직원 등록 (정보저장)
	public int sowAddEmployee(String searchArea, String emp_name, String emp_position) throws Exception;

	// 직원 목록
	public List<SowVO> selectEmployeeList(String searchArea) throws Exception;

	public CombinedSowDailyWorkLog getCombinedSowDailyWorkLog(String searchArea, String work_date) throws Exception;

	// 출장자 목록
	public List<SowVO> selectBtEmployeeList(String searchArea, String bt_inout) throws Exception;

	// 출장자 추가 (정보저장)
	public int sowAddBtEmployee(String emp_name, String sowDWL_work_name, String bt_inout, String searchArea) throws Exception;
	
	// 출장자 추가누계
	public List<SowVO> btSumOverTime(String searchArea, String bt_inout, String work_date) throws Exception;
	
	// 출장자 수정
	public int sowModBusinessTrip(List<SowVO> sowBusinessTrip, String searchArea, String login_id, String work_date) throws Exception;
}
