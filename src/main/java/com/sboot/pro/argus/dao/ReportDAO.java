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
	// 보고서 게시판 접속
	public List<ReportVO> selectReportList(String searchArea) throws Exception;
	
	// 일별 보고서 보기
	public List<ReportVO> selectDailyReport(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	// 월별 보고서 현장 추가
	public void insertTotalReport(@Param("addTotal") ReportVO addTotal, @Param("searchArea") String searchArea) throws Exception;
	
	// 보고서 글쓰기 양식
	public List<ReportVO> selectAddReportForm(String searchArea) throws Exception;
	
	// 보고서 글쓰기(정보저장)
	public void insertAddWorkReportList(@Param("searchArea") String searchArea, @Param("workReportList") List<ReportVO> workReportList) throws Exception;

	public List<ReportVO> selectReportForm(String searchArea) throws Exception;
}
