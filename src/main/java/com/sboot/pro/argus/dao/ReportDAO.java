package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.ReportVO;

@Mapper
@Repository("reportDAO")
public interface ReportDAO {
	public List<ReportVO> addReportForm(String searchArea) throws Exception;
}
