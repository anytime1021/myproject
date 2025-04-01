package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.apache.ibatis.annotations.Param;

import com.sboot.pro.argus.vo.ReportVO;

@Mapper
@Repository("reportDAO")
public interface ReportDAO {
	public List<ReportVO> addReportForm(String searchArea) throws Exception;
	
	public void insertAddWorkReportList(@Param("searchArea") String searchArea, @Param("workReportList") List<ReportVO> workReportList) throws Exception;

	public List<ReportVO> selectReportForm(String searchArea) throws Exception;
}
