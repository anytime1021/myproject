package com.sboot.pro.argus.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.vo.ReportVO;

@Service("reportService")
@Transactional(propagation = Propagation.REQUIRED)
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportDAO reportDAO;
	
	// 보고서 게시판 접속
	@Override
	public List<ReportVO> reportListJava(String searchArea) throws Exception {
		return reportDAO.selectReportList(searchArea);
	}
	
	// 일별 보고서 보기
	public List<ReportVO> dailyReportInfo(String searchArea, String work_date) throws Exception {
		return reportDAO.selectDailyReport(searchArea, work_date);
	}
	
	// 월별 보고서 현장 추가
	@Override
	public void addTotalReport(ReportVO addTotal, String searchArea) throws Exception{
		reportDAO.insertTotalReport(addTotal, searchArea);
	}
	
	// 일일 보고서 글쓰기 양식
	@Override
	public List<ReportVO> addReportForm(String searchArea) throws Exception {
		return reportDAO.selectAddReportForm(searchArea);
	}
	
	// 보고서 글쓰기(정보저장)
	@Override
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList) throws Exception {
		reportDAO.insertAddWorkReportList(searchArea, workReportList);
	}
	
	@Override
	public List<ReportVO> testForm(String searchArea) throws Exception {
		return reportDAO.selectReportForm(searchArea);
	}
}
