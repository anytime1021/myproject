package com.sboot.pro.argus.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.ReportVO;

@Mapper
@Repository("reportDAO")
public interface ReportDAO {
	// 일일 보고서 삭제
	public int deleteDailyReport(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 테스트
	public List<ReportVO> selectTest() throws Exception;
	
	// 테스트2
	public List<ReportVO> selectTestList2()  throws Exception;
	
	// 테스트3
	public List<ReportVO> selectTestList3(String s) throws Exception;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// 2025-04-17 전체 수정 후
	
	// 작업현황 행 삭제
	public int deleteTotalReportRow(@Param("work_num_total") int work_num_total) throws Exception;
	
	// 일일 보고서 게시판 글 추가
	public void insertWorkrate_board(@Param("searchArea") String searchArea, @Param("work_date") String work_date, @Param("board_title") String board_title) throws Exception;
	
	// 일일 보고서 게시판 삭제
	public int deleteDailyReportBoard(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public void insertAddWorkReportList(@Param("searchArea") String searchArea, @Param("workReportList") List<ReportVO> workReportList) throws Exception;
	// 일일 보고서 보기
	// 일일 보고서 보기 - 기본키 추출
	public List<Integer> selectPrimaryKey(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	public ReportVO selectHTW(String searchArea);
	// 일일 보고서 보기 - 값 가져오기
	public List<ReportVO> selectDailyReportView(@Param("work_date") String work_date, @Param("start_date") String start_date, @Param("fmonth_num") List<Integer> fmonth_num) throws Exception;

	// 일일 보고서 합계
	public ReportVO selectTotalSum(@Param("searchArea") String searchArea, @Param("start_date") String start_date, @Param("work_date") String work_date) throws Exception;
	public ReportVO selectDailySum(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;

	// 일일 보고서 합
	public ReportVO selectAddDailyReportSumForm(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 수정
	public void insertWorkReportUpdateLog(@Param("searchArea") String searchArea, @Param("work_date") String work_date, @Param("login_id") String login_id) throws Exception;
	public void updateWorkReportList(@Param("searchArea") String searchArea, @Param("modWorkReportList") List<ReportVO> modWorkReportList, @Param("work_date") String work_date) throws Exception;
	// 일일 보고서 수정 - 수정횟수 카운트
	public int getCountModLog(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	public List<ReportVO> selectModLog(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	// 일일 보고서 수정 - 날짜 가져오기
	public List<ReportVO> getModDate(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// -------------------------------------------------------------------------------------------------------------------

	// 단가 ajax ----------------------------------------------
	public void insertMethodQuantityCost(ReportVO cost) throws Exception;
	public List<ReportVO> selectUnitCost(String searchArea) throws Exception;
	public List<ReportVO> selectUnitCostWorkName(String searchArea) throws Exception;

	public void insertFmonth(@Param("fmonth_name") String fmonth_name, @Param("fmonth_profits") BigDecimal fmonth_profits, @Param("searchArea") String searchArea) throws Exception;;
	public List<ReportVO> selectFmonth(String searchArea) throws Exception;
	
	public int updateFmonth(ReportVO vo) throws Exception;
	public void deleteFmonth(ReportVO vo) throws Exception;
	
	// 추가
	public int insertReport(@Param("searchArea") String searchArea, @Param("board_title") String board_title, @Param("work_date") String work_date) throws Exception;

	// 날짜 검색
	public String selectWorkdate(String searchArea);
	
	// 이전날 정보 가져오기
	public List<ReportVO> selectWorkrateFormBefore(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 게시글 생성
	public String selectBoardTitle(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;

	// 게시글 수정
	public int updateReport(@Param("searchArea") String searchArea, @Param("board_title") String board_title, @Param("work_date") String work_date) throws Exception;

	//	public int countBoardList(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// SQL Injection Test
	public List<ReportVO> selectInformation(@Param("search") String search, @Param("searchArea") String searchArea) throws Exception;
}
