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
	
	// 일별 보고서 게시판 접속
	@Override
	public List<ReportVO> reportListJava(String searchArea) throws Exception {
		return reportDAO.selectReportList(searchArea);
	}
	
	// 일별 보고서 보기
	public List<ReportVO> dailyReportInfo(String searchArea, String board_date) throws Exception {
		return reportDAO.selectDailyReport(searchArea, board_date);
	}
	
	// 일일 보고서 글쓰기(정보저장)
	@Override
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList, String work_date) throws Exception {
		String board_title = work_date + " 보고서";
		reportDAO.insertWorkrate_board(board_title, work_date, searchArea);
		reportDAO.insertAddWorkReportList(searchArea, workReportList, work_date);
	}
	
	// 일일 보고서 수정
	@Override
	public void modWorkReportList(String searchArea, List<ReportVO> modWorkReportList, String work_date) throws Exception {
		reportDAO.updateWorkReport(searchArea, modWorkReportList, work_date);
	}
	
	// 월별 보고서 게시판 접속
	@Override
	public List<ReportVO> reportListTotalJava(String searchArea) throws Exception {
		return reportDAO.selectTotalReportList(searchArea);
	}
	
	// 월별 보고서 현장 추가
	@Override
	public void addTotalReport(ReportVO addTotal, String searchArea) throws Exception{
		reportDAO.insertTotalReport(addTotal, searchArea);
	}
	
	// 월별 전체량
	@Override
	public List<ReportVO> addReportForm(String searchArea, String work_date_total) throws Exception {
		return reportDAO.selectAddReportForm(searchArea, work_date_total);
	}
	
	@Override
	public List<ReportVO> testForm(String searchArea) throws Exception {
		return reportDAO.selectReportForm(searchArea);
	}
	
	// 테스트
	public List<ReportVO> selectTest() throws Exception{
		return reportDAO.selectTest();
	}
}
