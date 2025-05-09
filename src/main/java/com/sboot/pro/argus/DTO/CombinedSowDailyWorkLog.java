package com.sboot.pro.argus.DTO;

import java.util.List;

import com.sboot.pro.argus.vo.SowVO;

public class CombinedSowDailyWorkLog {
	private List<SowVO> sowDailyWorkLog;
	private List<SowVO> sumOverTime;
	public List<SowVO> getSowDailyWorkLog() {
		return sowDailyWorkLog;
	}
	public void setSowDailyWorkLog(List<SowVO> sowDailyWorkLog) {
		this.sowDailyWorkLog = sowDailyWorkLog;
	}
	public List<SowVO> getSumOverTime() {
		return sumOverTime;
	}
	public void setSumOverTime(List<SowVO> sumOverTime) {
		this.sumOverTime = sumOverTime;
	}
}
