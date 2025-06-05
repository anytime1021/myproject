package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.ResultsVO;

@Mapper
@Repository("resultsDAO")
public interface ResultsDAO {
	
	// 실적 추가 (정보저장)
	public int insertResultsList(@Param("searchArea") String searchArea, @Param("work_date") String work_date, @Param("addResultsList") List<ResultsVO> addResultsList) throws Exception;

	// 실적 보기
	public List<ResultsVO> selectResultsList(@Param("searchArea") String searchArea, @Param("start_date") String start_date, @Param("work_date") String work_date) throws Exception;
	public ResultsVO selectResultsSum(@Param("searchArea") String searchArea, @Param("start_date") String start_date, @Param("work_date") String work_date) throws Exception;

	// mixed
	public int insertResultsUpdateLog(@Param("searchArea") String searchArea, @Param("login_id") String login_id, @Param("work_date") String work_date) throws Exception;
	public int updateResultsList(@Param("searchArea") String searchArea, @Param("work_date") String work_date, @Param("modResultsList") List<ResultsVO> modResultsList) throws Exception;

	// 삭제
	public int deleteResults(@Param("searchArea") String searchArea, @Param("work_date") String work_date) throws Exception;
}
