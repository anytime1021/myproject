package com.sboot.pro.argus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.dao.SowDAO;
import com.sboot.pro.argus.vo.ReportVO;
import com.sboot.pro.argus.vo.SowVO;

@Service("sowService")
@Transactional(propagation = Propagation.REQUIRED)
public class SowServiceImpl implements SowService {

	@Autowired
	private SowDAO sowDAO;
	
	@Autowired
	private SowVO sowVO;
	
	@Autowired
	private ReportVO reportVO;
	
	@Autowired
	private ReportDAO reportDAO;
	
	// sow 게시판 접속
	@Override
	public SowVO sowBoardList(String searchArea) throws Exception {
		return sowDAO.selectSowBoardList(searchArea);
	}

	// sow 일일 추가 (정보저장) - 게시판 없을시 추가
	@Override
	public void sowAddDailyWorkLogList(String searchArea, List<SowVO> sowDailyWorkLogList, String work_date) throws Exception {
		sowDAO.insertSowDailyWorkLogList(searchArea, sowDailyWorkLogList, work_date);
		sowDAO.insertSowDailyWorkLogBoard(searchArea, work_date);
	}
	
	// sow 일일 보기
	@Override
	public List<SowVO> selectViewList(String searchArea, String work_date) throws Exception {
		return sowDAO.selectViewList(searchArea, work_date);
	}
	
	// sow 일일 보기 (추가 누계)
	@Override
	public CombinedSowDailyWorkLog getCombinedSowDailyWorkLog(String searchArea, String work_date) throws Exception {
		List<SowVO> selectViewList = new ArrayList<>();
		selectViewList = sowDAO.selectViewList(searchArea, work_date);
		String searchDate = work_date.substring(0,7) + "-01";
		List<SowVO> sumOverTime = new ArrayList<>();
		sumOverTime = sowDAO.selectSumOverTime(searchArea, work_date, searchDate);
		CombinedSowDailyWorkLog response = new CombinedSowDailyWorkLog();
		response.setSowDailyWorkLog(selectViewList);
		response.setSumOverTime(sumOverTime);
		return response;
	}
	
	@Override
	public List<SowVO> selectDayNightOvertime(String searchArea, String work_date) throws Exception {
		String start_date = work_date.substring(0,7) + "-01";
		return sowDAO.selectDayNightOvertime(searchArea, start_date, work_date);
	}
	
	@Override
	public List<SowVO> selectShiftType(String searchArea, String work_date) throws Exception {
		return sowDAO.selectShiftType(searchArea, work_date);
	}
	
	// 직원 등록(정보저장)
	@Override
	public int sowAddEmployee(String searchArea, String emp_name, String emp_position) throws Exception {
		return sowDAO.insertEmployee(searchArea, emp_name, emp_position);
	}
	
	// 직원 목록
	@Override
	public List<SowVO> selectEmployeeList(String searchArea) throws Exception {
		return sowDAO.selectEmployeeList(searchArea);
	}
}
