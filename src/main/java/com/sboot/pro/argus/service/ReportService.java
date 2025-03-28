package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;

public interface ReportService {
	public List<ReportVO> addReportForm(String searchArea) throws Exception;
}
