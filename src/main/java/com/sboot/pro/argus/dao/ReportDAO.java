package com.sboot.pro.argus.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.ReportVO;

@Mapper
@Repository("reportDAO")
public interface ReportDAO {
	// 일일 보고서 게시판 접속
	public List<ReportVO> selectReportList(String searchArea) throws Exception;
	
	// 일일 보고서 보기
	public List<ReportVO> selectDailyReport(@Param("searchArea") String searchArea, @Param("work_date") String board_date) throws Exception;
	
	// 일일 보고서 합
	public ReportVO selectAddDailyReportSumForm(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 게시판 글 추가
	public void insertWorkrate_board(@Param("board_title") String board_title, @Param("work_date") String work_date, @Param("searchArea") String searchArea) throws Exception;
	
	// 일일 보고서 글쓰기(정보저장)
	public void insertAddWorkReportList(@Param("searchArea") String searchArea, @Param("workReportList") List<ReportVO> workReportList, 
			@Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 수정
	public void updateWorkReport(@Param("searchArea") String searchArea, @Param("modWorkReportList") List<ReportVO> modWorkReportList, @Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 삭제
	public int deleteDailyReport(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 게시판 일일 보고서 제목 삭제 판단
	public int existDailyReport(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 일일 보고서 게시판 일일 보고서 제목 삭제
	public void deleteDailyReportBoard(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
//	// 월별 보고서 게시판 접속
//	public List<ReportVO> selectTotalReportList(@Param("searchArea") String searchArea, @Param("token") int token) throws Exception;
	
	// 월별 보고서 게시판 접속
	public List<ReportVO> selectTotalReportList(@Param("searchArea") String searchArea, @Param("tableName") String tableName) throws Exception;
	
	// 월별 보고서 현장 추가
	public void insertTotalReport(@Param("addTotal") ReportVO addTotal, @Param("searchArea") String searchArea) throws Exception;
	
	// 월별 전체량
	public List<ReportVO> selectAddReportForm(@Param("searchArea") String searchArea, @Param("work_date_total") String work_date_total) throws Exception;
	
	// 월별 전체량 합
	public ReportVO selectAddReportSumForm(@Param("searchArea") String searchArea, @Param("work_date_total") String work_date_total) throws Exception;

	// 월별 보고서 수정
	public void updateTotalReportList(@Param("searchArea") String searchArea, @Param("modTotalReportList") List<ReportVO> modTotalReportList, @Param("searchDate") String searchDate) throws Exception;
	
	// 월별 보고서 행 삭제
	public int deleteTotalReportRow(@Param("searchArea") String searchArea, @Param("work_name_total") String work_name_total, @Param("searchDate") String searchDate) throws Exception;
	
	// -------------------------------------------------------------------------------------------------------------------
	
	// sow 게시판 접속
	public ReportVO selectSowBoardList(String searchArea) throws Exception;
	
	// sow 일별 추가 폼 (작업목록 가져오기)
	public List<ReportVO> selectWorkName(@Param("searchArea") String searchArea, @Param("searchDate") String searchDate) throws Exception;
	
	// sow 일별 추가 폼
	public List<ReportVO> selectSowMonthList(@Param("searchArea") String searchArea, @Param("searchDate") String searchDate) throws Exception;
	
	// sow 월별 추가(정보저장)
	public int insertSowTotal(@Param("searchArea") String searchArea, @Param("sowDML_name") String sowDML_name, @Param("work_date") String work_date) throws Exception;
	
	// sow 월별 정보 가져오기
	public List<ReportVO> selectAddTotal(@Param("searchArea") String searchArea, @Param("searchDate") String searchDate) throws Exception;
	
	public List<ReportVO> selectReportForm(String searchArea) throws Exception;
	
	// 테스트
	public List<ReportVO> selectTest() throws Exception;
}
