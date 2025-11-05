package com.sboot.pro.argus.vo;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("workingDailyBaseVO")
@Data
public class WorkingDailyBaseVO {
	// 로그인
	private LoginVO loginVO;
	private String login_id;
	
	// 작업일보 전체
	private String work_date;
	private String login_work_area;
	private String weather;
	private String dayofweek;
	
	// 게시판
	private int board_num;
	private String board_title;
	private String board_date;
	private String board_area;
	private int row_num;
	
	// 변수상수 공통
	private int dummyInt;
	
	// 삭제여부 관련
	private String delete_date;
	private String YN;
	
	// 익월 보고서
	private String fmonth_name;
	private BigDecimal fmonth_profits;
	private String login_area;
	private int fmonth_num;
	private String fmonth_profits_comma;
	
	// 비고
	private String note;
	private String token;
	
	public LoginVO getLoginVO() {
		return loginVO;
	}
	public void setLoginVO(LoginVO loginVO) {
		this.loginVO = loginVO;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getWork_date() {
		return work_date;
	}
	public void setWork_date(String work_date) {
		this.work_date = work_date;
	}
	public String getLogin_work_area() {
		return login_work_area;
	}
	public void setLogin_work_area(String login_work_area) {
		this.login_work_area = login_work_area;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getDayofweek() {
		return dayofweek;
	}
	public void setDayofweek(String dayofweek) {
		this.dayofweek = dayofweek;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public String getBoard_area() {
		return board_area;
	}
	public void setBoard_area(String board_area) {
		this.board_area = board_area;
	}
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public int getDummyInt() {
		return dummyInt;
	}
	public void setDummyInt(int dummyInt) {
		this.dummyInt = dummyInt;
	}
	public String getDelete_date() {
		return delete_date;
	}
	public void setDelete_date(String delete_date) {
		this.delete_date = delete_date;
	}
	public String getYN() {
		return YN;
	}
	public void setYN(String yN) {
		YN = yN;
	}
	public String getFmonth_name() {
		return fmonth_name;
	}
	public void setFmonth_name(String fmonth_name) {
		this.fmonth_name = fmonth_name;
	}
	public BigDecimal getFmonth_profits() {
		return fmonth_profits;
	}
	public void setFmonth_profits(BigDecimal fmonth_profits) {
		this.fmonth_profits = fmonth_profits;
	}
	
	public String getFmonth_profits_comma() {
		return fmonth_profits_comma;
	}
	public void setFmonth_profits_comma(String fmonth_profits_comma) {
		this.fmonth_profits_comma = fmonth_profits_comma;
	}
	public String getLogin_area() {
		return login_area;
	}
	public void setLogin_area(String login_area) {
		this.login_area = login_area;
	}
	public int getFmonth_num() {
		return fmonth_num;
	}
	public void setFmonth_num(int fmonth_num) {
		this.fmonth_num = fmonth_num;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return "WorkingDailyBaseVO [loginVO=" + loginVO + ", login_id=" + login_id + ", work_date=" + work_date
				+ ", login_work_area=" + login_work_area + ", board_num=" + board_num + ", board_title=" + board_title
				+ ", board_date=" + board_date + ", board_area=" + board_area + ", row_num=" + row_num + ", dummyInt="
				+ dummyInt + ", delete_date=" + delete_date + ", YN=" + YN + ", fmonth_name=" + fmonth_name
				+ ", fmonth_profits=" + fmonth_profits + ", login_area=" + login_area + ", fmonth_num=" + fmonth_num
				+ "]";
	}
}
