package com.sboot.pro.argus.DTO;

import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;

public class CombinedReportResponse {
	private List<ReportVO> reportList;
    private ReportVO singleReport;
    
	public List<ReportVO> getReportList() {
		return reportList;
	}
	public void setReportList(List<ReportVO> reportList) {
		this.reportList = reportList;
	}
	public ReportVO getSingleReport() {
		return singleReport;
	}
	public void setSingleReport(ReportVO singleReport) {
		this.singleReport = singleReport;
	}
    
    
}
