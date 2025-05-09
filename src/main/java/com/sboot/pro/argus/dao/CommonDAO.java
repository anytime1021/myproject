package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

@Mapper
@Repository("commonDAO")
public interface CommonDAO {
	// 월별 보고서 게시판 접속
	public List<WorkingDailyBaseVO> selectTotalReportList(@Param("searchArea") String searchArea, @Param("tableName") String tableName) throws Exception;
	
}
