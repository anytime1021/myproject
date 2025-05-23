package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.ReportVO;
import com.sboot.pro.argus.vo.SowVO;

@Mapper
@Repository("sowDAO")
public interface SowDAO {
	// sow 게시판 접속
	public SowVO selectSowBoardList(String searchArea) throws Exception;

	// sow 일일 추가 (정보저장) - 게시판 없을시 추가
	public void insertSowDailyWorkLogList(@Param("searchArea") String searchArea, @Param("sowDailyWorkLogList") List<SowVO> sowDailyWorkLogList, @Param("work_date") String work_date) throws Exception;
	public int countBoard(@Param("searchArea") String searchArea, @Param("work_date") String work_date);
	public void insertSowDailyWorkLogBoard(@Param("searchArea") String searchArea, @Param("work_date") String work_date);
	public int countNameLength(String searchArea);
	public void insertBusinessTrip(@Param("searchArea") String searchArea, @Param("sowBusinessTrip") List<SowVO> sowBusinessTrip, @Param("work_date") String work_date) throws Exception;
	public int countBtNameLength(@Param("searchArea") String searchArea, @Param("bt_inout") String bt_inout) throws Exception;

	// sow 일일 보기
	public List<SowVO> selectViewList(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	public List<SowVO> selectSumOverTime(@Param("searchArea") String searchArea, @Param("work_date") String work_date, @Param("start_date") String start_date) throws Exception;
	
	public List<SowVO> selectDayNightOvertime(@Param("searchArea") String searchArea, @Param("start_date") String start_date, @Param("work_date") String work_date) throws Exception;
	public List<SowVO> selectShiftType(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
	
	public List<SowVO> selectBusinessTrip(@Param("searchArea") String searchArea, @Param("bt_inout") String bt_inout, @Param("work_date") String work_date) throws Exception;
	public int countBtViewList(@Param("searchArea") String searchArea, @Param("bt_inout") String bt_inout, @Param("work_date") String work_date) throws Exception;
	
	// sow 일일 추가 폼 (작업목록 가져오기)
	public List<SowVO> selectWorkName(@Param("searchArea") String searchArea) throws Exception;
	
	// 직원 목록
	public List<SowVO> selectEmployeeList(String searchArea) throws Exception;
	
	// 직원 등록 (정보저장)
	public int insertEmployee(@Param("searchArea") String searchArea, @Param("emp_name") String emp_name, @Param("emp_position") String emp_position) throws Exception;
	
	// 직원 수정 및 삭제 ajax
	public int updateEmployee(SowVO employee) throws Exception;
	public void deleteEmployee(SowVO dummyInt) throws Exception;

	// 출장자 목록
	public List<SowVO> selectBtEmployeeList(@Param("searchArea") String searchArea, @Param("bt_inout") String bt_inout) throws Exception;

	// 출장자 count - 테이블 구조 유지 조건
	public int countBtList(@Param("searchArea") String searchArea, @Param("bt_inout") String bt_inout) throws Exception;
	
	// 출장자 추가 (정보저장)
	public int insertAddBtEmployee(@Param("emp_name") String emp_name, @Param("sowDWL_work_name") String sowDWL_work_name, @Param("bt_inout") String bt_inout, @Param("searchArea") String searchArea) throws Exception;

	// 출장자 추가누계
	public List<SowVO> selectBtSumOverTime(@Param("searchArea") String searchArea, @Param("bt_inout") String bt_inout, @Param("start_date") String start_date, @Param("work_date") String work_date) throws Exception;
}
