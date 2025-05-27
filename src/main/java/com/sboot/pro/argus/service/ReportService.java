package com.sboot.pro.argus.service;

import java.math.BigDecimal;
import java.util.List;

import com.sboot.pro.argus.DTO.CombinedReportResponse;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.DTO.DailyReportWorkrate;
import com.sboot.pro.argus.vo.ReportVO;

public interface ReportService {
	// 일일 보고서 게시판 접속
	public List<ReportVO> reportListJava(String searchArea) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList, String board_title) throws Exception;

	// 일일 보고서 보기 - 1
	public List<ReportVO> dailyReportView(String searchArea, String work_date) throws Exception;
	
	// 일일 보고서 보기 - 2
	public DailyReportWorkrate dailyReportWorkrate(String searchArea, String work_date) throws Exception;
	
	// 일일 보고서 합계
	public CombinedReportResponse sumDailyMonth(String searchArea, String work_date) throws Exception;
	
	// 일일 보고서 수정
	public void modWorkReportList(String searchArea, List<ReportVO> modWorkReportList, String work_date, String login_id) throws Exception;
	public List<ReportVO> selectModLog(String searchArea, String work_date) throws Exception;
	
	public List<ReportVO> selectFmonth(String fmonth_name, BigDecimal fmonth_profits, String searchArea) throws Exception;

	// 일일 보고서 삭제
	public int removeDailyReport(String searchArea, String work_date) throws Exception;
	
//	// 월별 보고서 게시판 접속
//	public List<ReportVO> reportListTotalJava(String searchArea, int token) throws Exception;
	


//		
//	// 월별 전체량 합
//	public ReportVO addReportSumForm(String searchArea, String work_date_total) throws Exception;
	
		
	
	// -------------------------------------------------------------------------------------------------------------------
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// 2025-04-17 전체 수정 후
	
	// 작업현황 현장 추가 폼
	public List<ReportVO> selectWorkTotal(String searchArea) throws Exception;
	
	// 작업현황 현장 추가
	public void addTotalReport(List<ReportVO> addTotal, String searchArea) throws Exception;
	
	// 작업현황 현장 수정
	public void modTotalReportList(String searchArea, List<ReportVO> modTotalReportList) throws Exception;

	// 작업현황 행 삭제
	public int removeTotalReportRow(int work_num_total) throws Exception;

	// 일일 보고서 글쓰기 양식
//	public List<ReportVO> addReportForm(String searchArea) throws Exception;
	
	
	
	
	
	public int addReportMixed(String searchArea, String board_title, String work_date) throws Exception;
}
