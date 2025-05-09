package com.sboot.pro.argus.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sboot.pro.argus.DTO.CombinedReportResponse;
import com.sboot.pro.argus.DTO.DailyReportWorkrate;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.vo.ReportVO;

@Service("reportService")
@Transactional(propagation = Propagation.REQUIRED)
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ReportDAO reportDAO;
	
	// 일일 보고서 게시판 접속
	@Override
	public List<ReportVO> reportListJava(String searchArea) throws Exception {
		return reportDAO.selectReportList(searchArea);
	}
	
	// 일일 보고서 글쓰기(정보저장)
	@Override
	public void addWorkReportList(String searchArea, List<ReportVO> workReportList, String board_title) throws Exception {
		reportDAO.insertWorkrate_board(searchArea, workReportList.get(0).getWork_date(), board_title);
		reportDAO.insertAddWorkReportList(searchArea, workReportList);
	}
	
	// 일일 보고서 보기 - 1
	@Override
	public List<ReportVO> dailyReportView(String searchArea, String work_date) throws Exception {
		String start_date = work_date.substring(0,7) + "-01";
		// 일일보고서 기본키 추출
		List<Integer> work_num_total = reportDAO.selectWork_num_total(searchArea, work_date);
		// 일일보고서 값 가져오기
		return reportDAO.selectDailyReportView(work_date, start_date, work_num_total);
	}
	
	// 일일 보고서 보기 - 2 (1과 동시 존재해야 함)
	@Override
	public DailyReportWorkrate dailyReportWorkrate(String searchArea, String work_date) throws Exception {
		List<ReportVO> dailyReportView = new ArrayList<ReportVO>();
		dailyReportView = this.dailyReportView(searchArea, work_date);
		
//		ObjectMapper objectMapper = new ObjectMapper();
//		Iterator<ReportVO> iterator = dailyReportView.iterator();
//		
//		while(iterator.hasNext()) {
//			ReportVO vo = iterator.next();
//			try {
//				String json = objectMapper.writeValueAsString(vo);
//				System.out.println(json);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		// 일일 보고서 합계
		CombinedReportResponse result = this.sumDailyMonth(searchArea, work_date);
		ReportVO totalSum = result.getTotalSum();
		ReportVO dailySum = result.getDailySum();
		
		// xray, paut, 차량 가져오기
		List<ReportVO> dailyWorkTotalView = reportDAO.selectWorkTotalByView(searchArea);
		
		List<ReportVO> dailyReportViewMerged = new ArrayList<>();
		for (ReportVO dailyView : dailyReportView) {
		    for (ReportVO workTotal : dailyWorkTotalView) {
		        if (dailyView.getWork_num_total() == workTotal.getWork_num_total()) {
	
		            ReportVO merged = new ReportVO();
	
		            // 기본키 통합
		            merged.setWork_num_total(dailyView.getWork_num_total());
	
		            // 각각의 데이터를 매칭하여 set
		            merged.setWork_name(dailyView.getWork_name());
		            merged.setWork_amount_HTW1(dailyView.getWork_amount_HTW1());
		            merged.setWork_amount_HTW1_total(dailyView.getWork_amount_HTW1_total());
		            merged.setWork_amount_HTW2(dailyView.getWork_amount_HTW2());
		            merged.setWork_amount_HTW2_total(dailyView.getWork_amount_HTW2_total());
		            merged.setWork_amount_HTW3(dailyView.getWork_amount_HTW3());
		            merged.setWork_amount_HTW3_total(dailyView.getWork_amount_HTW3_total());
		            merged.setWork_amount_HTW4(dailyView.getWork_amount_HTW4());
		            merged.setWork_amount_HTW4_total(dailyView.getWork_amount_HTW4_total());
		            merged.setWork_amount_HTW5(dailyView.getWork_amount_HTW5());
		            merged.setWork_amount_HTW5_total(dailyView.getWork_amount_HTW5_total());
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
	public CombinedReportResponse sumDailyMonth(String searchArea, String work_date) throws Exception {
		String start_date = work_date.substring(0,7) + "-01";
		ReportVO totalSum = reportDAO.selectTotalSum(searchArea, start_date, work_date);
		ReportVO dailySum = reportDAO.selectDailySum(searchArea, work_date);
		CombinedReportResponse result = new CombinedReportResponse();
		result.setTotalSum(totalSum);
		result.setDailySum(dailySum);
		
		return result;
	}

	// 일일 보고서 수정
	@Override
	public void modWorkReportList(String searchArea, List<ReportVO> modWorkReportList, String work_date, String login_id) throws Exception {
		reportDAO.insertWorkReportUpdateLog(searchArea, work_date, login_id);
		reportDAO.updateWorkReport(searchArea, modWorkReportList, work_date);
	}
	
	public List<ReportVO> selectModLog(String searchArea, String work_date) throws Exception {
		return reportDAO.selectModLog(searchArea, work_date);
	}
	
	public List<ReportVO> selectFmonth(String fmonth_name, BigDecimal fmonth_profits, String searchArea) throws Exception {
		reportDAO.insertFmonth(fmonth_name, fmonth_profits, searchArea);
		List<ReportVO> selectFmonth = reportDAO.selectFmonth(searchArea);
		return selectFmonth;
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
	public void addTotalReport(List<ReportVO> addTotal, String searchArea) throws Exception{
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
}

