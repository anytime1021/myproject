package com.sboot.pro.argus.DTO;

import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;

public class CombinedReportResponse {
	private ReportVO totalSum;
    private ReportVO dailySum;
	public ReportVO getTotalSum() {
		return totalSum;
	}
	public void setTotalSum(ReportVO totalSum) {
		this.totalSum = totalSum;
	}
	public ReportVO getDailySum() {
		return dailySum;
	}
	public void setDailySum(ReportVO dailySum) {
		this.dailySum = dailySum;
	}

}
