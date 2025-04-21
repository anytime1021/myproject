package com.sboot.pro.argus.vo;


import java.util.Date;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("reportVO")
@Data
public class ReportVO {
	
	//로그인
	private LoginVO loginVO;
	private String login_id;
	
	// 변수
	private int work_num;
	private String work_name;
	private int work_amount_RT;
	private int work_amount_PAUT;
	private int work_amount_TOFD;
	private int work_amount_UT;
	private int work_amount_MPT;
	private int work_manpower;
	private String work_xray;
	private String work_PAUT;
	private String work_charyang;
	private String work_date;
	private String login_work_area;
	private String work_title;
	private long work_price;
	private String update_date;
	
	// 변수 배열
	private int work_amount_RTArray;
	private int work_amount_PAUTArray;
	private int work_amount_TOFDArray;
	private int work_amount_UTArray;
	private int work_amount_MPTArray;
	private int work_manpowerArray;
	private String work_xrayArray;
	private String work_PAUTArray;
	private String work_charyangArray;
	
	// 전체 고정값
	private int work_num_total;
	private int work_amount_RT_total;
	private int work_amount_PAUT_total;
	private int work_amount_TOFD_total;
	private int work_amount_UT_total;
	private int work_amount_MPT_total;
	private int work_manpower_total;
	public int getWork_manpower_total() {
		return work_manpower_total;
	}

	public void setWork_manpower_total(int work_manpower_total) {
		this.work_manpower_total = work_manpower_total;
	}

	private String work_name_total;
	private String work_xray_total;
	private String work_PAUT_total;
	private String work_charyang_total;
	private String login_work_area_total;
	private String work_title_total;

	
	// 월별 게시판
	private int board_num;
	private String board_title;
	private String board_date;
	private String board_area;
	
	// sow 일별
	private String sowDWL_name;
	private String sowDWL_work_name;
	private String sowDWL_shift;
	private int sowDWL_hours;
	private int sowDWL_overtime;
	
	// sow 월별
	private String sowMWL_name;
	private String sowMWL_work_name;
	
	// 필요한 임의 숫자 넣을때 (예를들면 DB에서 AS 사용한 column)
	private int dummyInt;
	
	// 공통 변수
	private String delete_date;
	private String YN;
	private String work_date_total;
	
	// 게시판 번호
	private int row_num;

	public int getWork_num() {
		return work_num;
	}

	public void setWork_num(int work_num) {
		this.work_num = work_num;
	}

	public String getWork_name() {
		return work_name;
	}

	public void setWork_name(String work_name) {
		this.work_name = work_name;
	}

	public int getWork_amount_RT() {
		return work_amount_RT;
	}

	public void setWork_amount_RT(int work_amount_RT) {
		this.work_amount_RT = work_amount_RT;
	}

	public int getWork_amount_PAUT() {
		return work_amount_PAUT;
	}

	public void setWork_amount_PAUT(int work_amount_PAUT) {
		this.work_amount_PAUT = work_amount_PAUT;
	}

	public int getWork_amount_TOFD() {
		return work_amount_TOFD;
	}

	public void setWork_amount_TOFD(int work_amount_TOFD) {
		this.work_amount_TOFD = work_amount_TOFD;
	}

	public int getWork_amount_UT() {
		return work_amount_UT;
	}

	public void setWork_amount_UT(int work_amount_UT) {
		this.work_amount_UT = work_amount_UT;
	}

	public int getWork_amount_MPT() {
		return work_amount_MPT;
	}

	public void setWork_amount_MPT(int work_amount_MPT) {
		this.work_amount_MPT = work_amount_MPT;
	}

	public int getWork_manpower() {
		return work_manpower;
	}

	public void setWork_manpower(int work_manpower) {
		this.work_manpower = work_manpower;
	}

	public String getWork_xray() {
		return work_xray;
	}

	public void setWork_xray(String work_xray) {
		this.work_xray = work_xray;
	}

	public String getWork_PAUT() {
		return work_PAUT;
	}

	public void setWork_PAUT(String work_PAUT) {
		this.work_PAUT = work_PAUT;
	}

	public String getWork_charyang() {
		return work_charyang;
	}

	public void setWork_charyang(String work_charyang) {
		this.work_charyang = work_charyang;
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

	public String getWork_title() {
		return work_title;
	}

	public void setWork_title(String work_title) {
		this.work_title = work_title;
	}

	public long getWork_price() {
		return work_price;
	}

	public void setWork_price(long work_price) {
		this.work_price = work_price;
	}

	public int getWork_amount_RTArray() {
		return work_amount_RTArray;
	}

	public void setWork_amount_RTArray(int work_amount_RTArray) {
		this.work_amount_RTArray = work_amount_RTArray;
	}

	public int getWork_amount_PAUTArray() {
		return work_amount_PAUTArray;
	}

	public void setWork_amount_PAUTArray(int work_amount_PAUTArray) {
		this.work_amount_PAUTArray = work_amount_PAUTArray;
	}

	public int getWork_amount_TOFDArray() {
		return work_amount_TOFDArray;
	}

	public void setWork_amount_TOFDArray(int work_amount_TOFDArray) {
		this.work_amount_TOFDArray = work_amount_TOFDArray;
	}

	public int getWork_amount_UTArray() {
		return work_amount_UTArray;
	}

	public void setWork_amount_UTArray(int work_amount_UTArray) {
		this.work_amount_UTArray = work_amount_UTArray;
	}

	public int getWork_amount_MPTArray() {
		return work_amount_MPTArray;
	}

	public void setWork_amount_MPTArray(int work_amount_MPTArray) {
		this.work_amount_MPTArray = work_amount_MPTArray;
	}

	public int getWork_manpowerArray() {
		return work_manpowerArray;
	}

	public void setWork_manpowerArray(int work_manpowerArray) {
		this.work_manpowerArray = work_manpowerArray;
	}

	public String getWork_xrayArray() {
		return work_xrayArray;
	}

	public void setWork_xrayArray(String work_xrayArray) {
		this.work_xrayArray = work_xrayArray;
	}

	public String getWork_PAUTArray() {
		return work_PAUTArray;
	}

	public void setWork_PAUTArray(String work_PAUTArray) {
		this.work_PAUTArray = work_PAUTArray;
	}

	public String getWork_charyangArray() {
		return work_charyangArray;
	}

	public void setWork_charyangArray(String work_charyangArray) {
		this.work_charyangArray = work_charyangArray;
	}

	public int getWork_num_total() {
		return work_num_total;
	}

	public void setWork_num_total(int work_num_total) {
		this.work_num_total = work_num_total;
	}

	public String getWork_name_total() {
		return work_name_total;
	}

	public void setWork_name_total(String work_name_total) {
		this.work_name_total = work_name_total;
	}

	public String getWork_xray_total() {
		return work_xray_total;
	}

	public void setWork_xray_total(String work_xray_total) {
		this.work_xray_total = work_xray_total;
	}

	public String getWork_PAUT_total() {
		return work_PAUT_total;
	}

	public void setWork_PAUT_total(String work_PAUT_total) {
		this.work_PAUT_total = work_PAUT_total;
	}

	public String getWork_charyang_total() {
		return work_charyang_total;
	}

	public void setWork_charyang_total(String work_charyang_total) {
		this.work_charyang_total = work_charyang_total;
	}

	public String getLogin_work_area_total() {
		return login_work_area_total;
	}

	public void setLogin_work_area_total(String login_work_area_total) {
		this.login_work_area_total = login_work_area_total;
	}

	public String getWork_title_total() {
		return work_title_total;
	}

	public void setWork_title_total(String work_title_total) {
		this.work_title_total = work_title_total;
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

	public String getSowDWL_name() {
		return sowDWL_name;
	}

	public void setSowDWL_name(String sowDWL_name) {
		this.sowDWL_name = sowDWL_name;
	}

	public String getSowDWL_work_name() {
		return sowDWL_work_name;
	}

	public void setSowDWL_work_name(String sowDWL_work_name) {
		this.sowDWL_work_name = sowDWL_work_name;
	}

	public String getSowDWL_shift() {
		return sowDWL_shift;
	}

	public void setSowDWL_shift(String sowDWL_shift) {
		this.sowDWL_shift = sowDWL_shift;
	}

	public int getSowDWL_hours() {
		return sowDWL_hours;
	}

	public void setSowDWL_hours(int sowDWL_hours) {
		this.sowDWL_hours = sowDWL_hours;
	}

	public int getSowDWL_overtime() {
		return sowDWL_overtime;
	}

	public void setSowDWL_overtime(int sowDWL_overtime) {
		this.sowDWL_overtime = sowDWL_overtime;
	}

	public String getSowMWL_name() {
		return sowMWL_name;
	}

	public void setSowMWL_name(String sowMWL_name) {
		this.sowMWL_name = sowMWL_name;
	}

	public String getSowMWL_work_name() {
		return sowMWL_work_name;
	}

	public void setSowMWL_work_name(String sowMWL_work_name) {
		this.sowMWL_work_name = sowMWL_work_name;
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

	public int getRow_num() {
		return row_num;
	}

	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}

	public String getWork_date_total() {
		return work_date_total;
	}

	public void setWork_date_total(String work_date_total) {
		this.work_date_total = work_date_total;
	}

	public int getWork_amount_RT_total() {
		return work_amount_RT_total;
	}

	public void setWork_amount_RT_total(int work_amount_RT_total) {
		this.work_amount_RT_total = work_amount_RT_total;
	}

	public int getWork_amount_PAUT_total() {
		return work_amount_PAUT_total;
	}

	public void setWork_amount_PAUT_total(int work_amount_PAUT_total) {
		this.work_amount_PAUT_total = work_amount_PAUT_total;
	}

	public int getWork_amount_TOFD_total() {
		return work_amount_TOFD_total;
	}

	public void setWork_amount_TOFD_total(int work_amount_TOFD_total) {
		this.work_amount_TOFD_total = work_amount_TOFD_total;
	}

	public int getWork_amount_UT_total() {
		return work_amount_UT_total;
	}

	public void setWork_amount_UT_total(int work_amount_UT_total) {
		this.work_amount_UT_total = work_amount_UT_total;
	}

	public int getWork_amount_MPT_total() {
		return work_amount_MPT_total;
	}

	public void setWork_amount_MPT_total(int work_amount_MPT_total) {
		this.work_amount_MPT_total = work_amount_MPT_total;
	}

	public String getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

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


}


