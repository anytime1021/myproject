package com.sboot.pro.argus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.DTO.CombinedReportResponse;
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
	
	// 일일 보고서 삭제
	@Override
	public int removeDailyReport(String searchArea, String work_date) throws Exception {
		int result = reportDAO.deleteDailyReport(searchArea, work_date);
		int result1 = reportDAO.existDailyReport(searchArea, work_date);
		// result2, 3 추가 예정
		int result2 = 0;
		int result3 = 0;
		if (result1 == 0 && result2 == 0 && result3 == 0) {
			reportDAO.deleteDailyReportBoard(searchArea, work_date);
		}
		return result;
	}
	
	// 월별 보고서 게시판 접속
//	@Override
//	public List<ReportVO> reportListTotalJava(String searchArea, int token) throws Exception {
//			return reportDAO.selectTotalReportList(searchArea, token);
//	}
	
	@Override
	public List<ReportVO> reportListTotalJava(String searchArea, String tableName) throws Exception {
			return reportDAO.selectTotalReportList(searchArea, tableName);
	}
	
	// 월별 보고서 현장 추가
	@Override
	public void addTotalReport(ReportVO addTotal, String searchArea) throws Exception{
		reportDAO.insertTotalReport(addTotal, searchArea);
	}
	
//	// 월별 전체량
	@Override
	public List<ReportVO> addReportForm(String searchArea, String work_date_total) throws Exception {
		return reportDAO.selectAddReportForm(searchArea, work_date_total);
	}
//	
//	// 월별 전체량 합
//	@Override
//	public ReportVO addReportSumForm(String searchArea, String work_date_total) throws Exception {
//		return reportDAO.selectAddReortSumForm(searchArea, work_date_total);
//	}
	
	// 보고서 일별 / 월별 한번에 가져오기
	@Override
	public CombinedReportResponse getCombinedReport(String searchArea, String searchDate) throws Exception {
	    List<ReportVO> addReport = new ArrayList<ReportVO>();
	    ReportVO addReport_sum = new ReportVO();
	    if(searchDate.length() == 7) {
	    	addReport = reportDAO.selectAddReportForm(searchArea, searchDate);
	    	addReport_sum = reportDAO.selectAddReportSumForm(searchArea, searchDate);
	    } else if (searchDate.length() == 10) {
	    	addReport = reportDAO.selectDailyReport(searchArea, searchDate);
	    	addReport_sum = reportDAO.selectAddDailyReportSumForm(searchArea, searchDate);
	    } else {

	    }
	    CombinedReportResponse response = new CombinedReportResponse();
	    response.setReportList(addReport);
	    response.setSingleReport(addReport_sum);

	    return response;
	}
	
	// 월별 보고서 수정
	@Override
	public void modTotalReportList(String searchArea, List<ReportVO> modTotalReportList, String searchDate) throws Exception {
		reportDAO.updateTotalReportList(searchArea, modTotalReportList, searchDate);
	}
	
	// 월별 보고서 행 삭제
	@Override
	public int removeTotalReportRow(String searchArea, String work_name_total, String searchDate) throws Exception {
		reportDAO.deleteTotalReportRow(searchArea, work_name_total, searchDate);
		return 1;
	}
	
	// -------------------------------------------------------------------------------------------------------------------
	
	// sow 게시판 접속
	public ReportVO sowBoardList(String searchArea) throws Exception {
		return reportDAO.selectSowBoardList(searchArea);
	}
	
	// sow 일별 추가 폼
	public List<ReportVO> selectSowMonthList(String searchArea, String searchDate) throws Exception {
		return reportDAO.selectSowMonthList(searchArea, searchDate);
	}
	
	// sow 월별 추가(정보저장)
	public int sowAddTotal(String searchArea, String sowDML_name, String work_date) throws Exception {
		return reportDAO.insertSowTotal(searchArea, sowDML_name, work_date);
	}
	
	// sow 월별 정보 가져오기
	public List<ReportVO> selectAddTotal(String searchArea, String searchDate) throws Exception {
		return reportDAO.selectAddTotal(searchArea, searchDate);
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
