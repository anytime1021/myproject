package com.sboot.pro.argus.DTO;

import java.util.List;

import com.sboot.pro.argus.vo.ReportVO;
import com.sboot.pro.argus.vo.ResultsVO;
import com.sboot.pro.argus.vo.SowVO;

public class DailyReportListDTO {
	private String work_date;
	private String board_title;

	private List<ReportVO> reportList;
	
	private List<SowVO> sowList;
	private List<SowVO> btInList;
	private List<SowVO> btOutList;
	private List<ResultsVO> resultsList;
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public List<ReportVO> getReportList() {
		return reportList;
	}
	public void setReportList(List<ReportVO> reportList) {
		this.reportList = reportList;
	}
	public List<SowVO> getSowList() {
		return sowList;
	}
	public void setSowList(List<SowVO> sowList) {
		this.sowList = sowList;
	}
	public List<SowVO> getBtInList() {
		return btInList;
	}
	public void setBtInList(List<SowVO> btInList) {
		this.btInList = btInList;
	}
	public List<SowVO> getBtOutList() {
		return btOutList;
	}
	public void setBtOutList(List<SowVO> btOutList) {
		this.btOutList = btOutList;
	}
	public List<ResultsVO> getResultsList() {
		return resultsList;
	}
	public void setResultsList(List<ResultsVO> resultsList) {
		this.resultsList = resultsList;
	}
}
