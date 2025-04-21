package com.sboot.pro.argus.DTO;

import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;

public class DailyReportWorkrate {
	private List<ReportVO> dailyReportViewMerged;
    private ReportVO totalSum;
    private ReportVO dailySum;

    public DailyReportWorkrate(List<ReportVO> merged, ReportVO totalSum, ReportVO dailySum) {
        this.dailyReportViewMerged = merged;
        this.totalSum = totalSum;
        this.dailySum = dailySum;
    }

    public List<ReportVO> getDailyReportViewMerged() {
        return dailyReportViewMerged;
    }

    public ReportVO getTotalSum() {
        return totalSum;
    }

    public ReportVO getDailySum() {
        return dailySum;
    }
}
