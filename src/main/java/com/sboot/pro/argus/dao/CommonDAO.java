package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

@Mapper
@Repository("commonDAO")
public interface CommonDAO {
	// 게시판 접속 공용
	public List<WorkingDailyBaseVO> selectTotalReportList(@Param("searchArea") String searchArea, @Param("tableName") String tableName, @Param("offset") int offset, @Param("limit") int limit) throws Exception;
	
	// 게시판 페이징 카운트
	public int selectReportCount(@Param("searchArea") String searchArea, @Param("tableName") String tableName) throws Exception;
}
