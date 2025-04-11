package com.sboot.pro.argus.DTO;

import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;

public class CombinedSowDailyWorkLog {
	private List<ReportVO> sowDailyWorkLog;
	private List<ReportVO> sumOverTime;
	public List<ReportVO> getSowDailyWorkLog() {
		return sowDailyWorkLog;
	}
	public void setSowDailyWorkLog(List<ReportVO> sowDailyWorkLog) {
		this.sowDailyWorkLog = sowDailyWorkLog;
	}
	public List<ReportVO> getSumOverTime() {
		return sumOverTime;
	}
	public void setSumOverTime(List<ReportVO> sumOverTime) {
		this.sumOverTime = sumOverTime;
	}
}
