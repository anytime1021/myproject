package com.sboot.pro.argus.service;

import java.util.Date;
import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;

public interface ReportService {
	
	// 보고서 게시판 접속
	public List<ReportVO> reportListJava(String searchArea) throws Exception;
	
	// 일별 보고서 보기
	public List<ReportVO> dailyReportInfo(String searchArea, String work_date) throws Exception;
	
	// 월별 보고서 현장 추가
	public void addTotalReport(ReportVO addTotal, String searchArea) throws Exception;
	
	// 일일 보고서 글쓰기 양식
	public List<ReportVO> addReportForm(String searchArea) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList) throws Exception;
	


	public List<ReportVO> testForm(String searchArea) throws Exception;
}
