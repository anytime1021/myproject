package com.sboot.pro.argus.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.DTO.CombinedReportResponse;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.DTO.DailyReportWorkrate;
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
	

	

//	
//	// 월별 전체량 합
//	@Override
//	public ReportVO addReportSumForm(String searchArea, String work_date_total) throws Exception {
//		return reportDAO.selectAddReortSumForm(searchArea, work_date_total);
//	}
	

	

	

	
	// -------------------------------------------------------------------------------------------------------------------
	
	// sow 게시판 접속
	@Override
	public ReportVO sowBoardList(String searchArea) throws Exception {
		return reportDAO.selectSowBoardList(searchArea);
	}
	
	// sow 일별 추가 폼
	@Override
	public List<ReportVO> selectSowMonthList(String searchArea, String searchDate) throws Exception {
		return reportDAO.selectSowMonthList(searchArea, searchDate);
	}
	
	// sow 일별 추가 (정보저장) - 게시판 없을시 추가
	@Override
	public void sowAddDailyWorkLogList(String searchArea, List<ReportVO> sowDailyWorkLogList, String work_date) throws Exception {
		reportDAO.insertSowDailyWorkLogList(searchArea, sowDailyWorkLogList, work_date);
		int result = reportDAO.countBoard(searchArea, work_date);
		if(result == 0) {
			reportDAO.insertSowDailyWorkLogBoard(searchArea, work_date);
		}
	}
	
	// sow 일별 보기
	@Override
	public List<ReportVO> selectViewList(String searchArea, String work_date) throws Exception {
		return reportDAO.selectViewList(searchArea, work_date);
	}
	
	@Override
	public CombinedSowDailyWorkLog getCombinedSowDailyWorkLog(String searchArea, String work_date) throws Exception {
		List<ReportVO> selectViewList = new ArrayList<>();
		selectViewList = reportDAO.selectViewList(searchArea, work_date);
		String searchDate = work_date.substring(0,7) + "-01";
		List<ReportVO> sumOverTime = new ArrayList<>();
		sumOverTime = reportDAO.selectSumOverTime(searchArea, work_date, searchDate);
		CombinedSowDailyWorkLog response = new CombinedSowDailyWorkLog();
		response.setSowDailyWorkLog(selectViewList);
		response.setSumOverTime(sumOverTime);
		
		return response;
	}
	
	
	// sow 월별 추가(정보저장)
	@Override
	public int sowAddTotal(String searchArea, String sowMWL_name, String work_date) throws Exception {
		return reportDAO.insertSowTotal(searchArea, sowMWL_name, work_date);
	}
	
	// sow 월별 이름 가져오기
	@Override
	public List<ReportVO> selectAddSowTotal(String searchArea, String searchDate) throws Exception {
		return reportDAO.selectAddSowTotal(searchArea, searchDate);
	}
	
	
	@Override
	public List<ReportVO> testForm(String searchArea) throws Exception {
		return reportDAO.selectReportForm(searchArea);
	}
	
	// 테스트
	public List<ReportVO> selectTest() throws Exception{
		return reportDAO.selectTest();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// 2025-04-17 전체 수정 후
	
	// 작업현황 현장 추가 폼
	public List<ReportVO> selectWorkTotal(String searchArea) throws Exception {
		return reportDAO.selectWorkTotal(searchArea);
	}
	
	// 작업현황 현장 추가
	@Override
	public void addTotalReport(ReportVO addTotal, String searchArea) throws Exception{
		reportDAO.insertTotalReport(addTotal, searchArea);
	}
	
	// 작업현황 현장 수정폼
	@Override
	public void modTotalReportList(String searchArea, List<ReportVO> modTotalReportList) throws Exception {
		reportDAO.updateTotalReportList(searchArea, modTotalReportList);
	}
	
	// 작업현황 행 삭제
	@Override
	public int removeTotalReportRow(int work_num_total) throws Exception {
		reportDAO.deleteTotalReportRow(work_num_total);
		return 1;
	}
//	// 일일 보고서 글쓰기 양식
//	@Override
//	public List<ReportVO> addReportForm(String searchArea) throws Exception {
//		return reportDAO.selectAddReportForm(searchArea);
//	}
	
	// 일일 보고서 글쓰기(정보저장)
	@Override
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList, String board_title) throws Exception {
		reportDAO.insertWorkrate_board(searchArea, workReportList.get(0).getWork_date(), board_title);
		reportDAO.insertAddWorkReportList(searchArea, workReportList);
	}
	
	// 일일 보고서 보기 - 1
	@Override
	public List<ReportVO> dailyReportView(String searchArea, String board_date) throws Exception {
		String start_date = board_date.substring(0,7) + "-01";
		// 일일보고서 기본키 추출
		List<Integer> work_num_total = reportDAO.selectWork_num_total(searchArea, board_date);
		// 일일보고서 값 가져오기
		return reportDAO.selectDailyReportView(board_date, start_date, work_num_total);
	}
	
	// 일일 보고서 보기 - 2 (1과 동시 존재해야 함)
	@Override
	public DailyReportWorkrate dailyReportWorkrate(String searchArea, String board_date) throws Exception {
		List<ReportVO> dailyReportView = new ArrayList<ReportVO>();
		dailyReportView = this.dailyReportView(searchArea, board_date);
		
		// 일일 보고서 합계
		CombinedReportResponse result = this.sumDailyMonth(searchArea, board_date);
		ReportVO totalSum = result.getTotalSum();
		ReportVO dailySum = result.getDailySum();
		
		// xray, paut, 차량 가져오기
		List<ReportVO> dailyWorkTotalView = this.selectWorkTotal(searchArea);
		
		List<ReportVO> dailyReportViewMerged = new ArrayList<>();
		for (ReportVO dailyView : dailyReportView) {
		    for (ReportVO workTotal : dailyWorkTotalView) {
		        if (dailyView.getWork_num_total() == workTotal.getWork_num_total()) {
	
		            ReportVO merged = new ReportVO();
	
		            // 기본키 통합
		            merged.setWork_num_total(dailyView.getWork_num_total());
	
		            // 각각의 데이터를 매칭하여 set
		            merged.setWork_name(dailyView.getWork_name());
		            merged.setWork_amount_RT(dailyView.getWork_amount_RT());
		            merged.setWork_amount_RT_total(dailyView.getWork_amount_RT_total());
		            merged.setWork_amount_PAUT(dailyView.getWork_amount_PAUT());
		            merged.setWork_amount_PAUT_total(dailyView.getWork_amount_PAUT_total());
		            merged.setWork_amount_TOFD(dailyView.getWork_amount_TOFD());
		            merged.setWork_amount_TOFD_total(dailyView.getWork_amount_TOFD_total());
		            merged.setWork_amount_UT(dailyView.getWork_amount_UT());
		            merged.setWork_amount_UT_total(dailyView.getWork_amount_UT_total());
		            merged.setWork_amount_MPT(dailyView.getWork_amount_MPT());
		            merged.setWork_amount_MPT_total(dailyView.getWork_amount_MPT_total());
		            merged.setWork_manpower(dailyView.getWork_manpower());
		            merged.setWork_manpower_total(dailyView.getWork_manpower_total());
		            
		            merged.setWork_xray_total(workTotal.getWork_xray_total());
		            merged.setWork_PAUT_total(workTotal.getWork_PAUT_total());
		            merged.setWork_charyang_total(workTotal.getWork_charyang_total());
	
		            dailyReportViewMerged.add(merged);
		            break; // 중복 방지용
		        }
		    }
		}
		
		return new DailyReportWorkrate(dailyReportViewMerged, totalSum, dailySum);
	}
	
	// 일일 보고서 합계
	@Override
	public CombinedReportResponse sumDailyMonth(String searchArea, String board_date) throws Exception {
		String start_date = board_date.substring(0,7) + "-01";
		ReportVO totalSum = reportDAO.selectTotalSum(searchArea, start_date, board_date);
		ReportVO dailySum = reportDAO.selectDailySum(searchArea, board_date);
		CombinedReportResponse result = new CombinedReportResponse();
		result.setTotalSum(totalSum);
		result.setDailySum(dailySum);
		
		return result;
	}
	
	// 일일 보고서 수정
	@Override
	public void modWorkReportList(String searchArea, List<ReportVO> modWorkReportList, String board_date, String login_id) throws Exception {
		reportDAO.insertWorkReportUpdateLog(searchArea, board_date, login_id);
		reportDAO.updateWorkReport(searchArea, modWorkReportList, board_date);
	}
	
	public List<ReportVO> selectModLog(String searchArea, String board_date) throws Exception {
		return reportDAO.selectModLog(searchArea, board_date);
	}
}

