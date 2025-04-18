package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.DTO.CombinedReportResponse;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.vo.ReportVO;

public interface ReportService {
	// 일별 보고서 게시판 접속
	public List<ReportVO> reportListJava(String searchArea) throws Exception;
	
	// 일별 보고서 보기
	public List<ReportVO> dailyReportInfo(String searchArea, String work_dateStr) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList, String work_date) throws Exception;
	
	// 일일 보고서 수정
	public void modWorkReportList(String searchArea, List<ReportVO> modWorkReportList, String work_date) throws Exception;
	
	// 일일 보고서 삭제
	public int removeDailyReport(String searchArea, String work_date) throws Exception;
	
//	// 월별 보고서 게시판 접속
//	public List<ReportVO> reportListTotalJava(String searchArea, int token) throws Exception;
	
	// 월별 보고서 게시판 접속
	public List<ReportVO> reportListTotalJava(String searchArea, String tableName) throws Exception;
	
//	// 월별 전체량
	public List<ReportVO> addReportForm(String searchArea, String work_date_total) throws Exception;
//		
//	// 월별 전체량 합
//	public ReportVO addReportSumForm(String searchArea, String work_date_total) throws Exception;
	
	// 보고서 일별 / 월별 한번에 가져오기
	public CombinedReportResponse getCombinedReport(String searchArea, String searchDate) throws Exception;
	
	// 월별 보고서 현장 추가
	public void addTotalReport(ReportVO addTotal, String searchArea) throws Exception;
	
	// 월별 보고서 수정
	public void modTotalReportList(String searchArea, List<ReportVO> modTotalReportList, String searchDate) throws Exception;
	
	// 월별 보고서 행 삭제
	public int removeTotalReportRow(String searchArea, String work_name_total, String searchDate) throws Exception;
	
	// -------------------------------------------------------------------------------------------------------------------
	
	// sow 게시판 접속
	public ReportVO sowBoardList(String searchArea) throws Exception;
	
	// sow 일별 추가 폼
	public List<ReportVO> selectSowMonthList(String searchArea, String searchDate) throws Exception;
	
	// sow 일별 추가 (정보저장)
	public void sowAddDailyWorkLogList(String searchArea, List<ReportVO> sowDailyWorkLogList, String work_date) throws Exception;
	
	// sow 일별 보기
	public List<ReportVO> selectViewList(String searchArea, String work_date) throws Exception;
	
	public CombinedSowDailyWorkLog getCombinedSowDailyWorkLog(String searchArea, String work_date) throws Exception;
	
	// sow 월별 추가 (정보저장)
	public int sowAddTotal(String searchArea, String sowMWL_name, String work_date) throws Exception;
	
	// sow 월별 이름 가져오기
	public List<ReportVO> selectAddTotal(String searchArea, String searchDate) throws Exception;
	
	
	public List<ReportVO> testForm(String searchArea) throws Exception;
	
	// 테스트
	public List<ReportVO> selectTest() throws Exception;
}
