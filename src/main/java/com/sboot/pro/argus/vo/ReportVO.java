package com.sboot.pro.argus.vo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.sboot.pro.argus.service.ReportService;
import com.sboot.pro.argus.service.ReportServiceImpl;

import lombok.Data;

@Component("reportVO")
@Data
public class ReportVO {
	
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
	private String login_work_area;
	
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
	private String work_name_total;
	private int work_amount_RT_total;
	private int work_amount_PAUT_total;
	private int work_amount_TOFD_total;
	private int work_amount_UT_total;
	private int work_amount_MPT_total;
	private int work_manpower_total;
	private String work_xray_total;
	private String work_PAUT_total;
	private String work_charyang_total;
	private String login_work_area_total;
	
	public ReportVO() {
		this.work_amount_RTArray = work_amount_RTArray;
		this.work_amount_PAUTArray = work_amount_PAUTArray;
		this.work_amount_TOFDArray = work_amount_TOFDArray;
		this.work_amount_UTArray = work_amount_UTArray;
		this.work_amount_MPTArray = work_amount_MPTArray;
		this.work_manpowerArray = work_manpowerArray;
		this.work_xrayArray = work_xrayArray;
		this.work_PAUTArray = work_PAUTArray;
		this.work_charyangArray = work_charyangArray;
	}

	//	public ReportVO(int work_amount_RTArray, int work_amount_PAUTArray, int work_amount_TOFDArray, int work_amount_UTArray,
	//	int work_amount_MPTArray, int work_manpowerArray, Arraying work_xrayArray, Arraying work_PAUTArray, Arraying work_charyangArray) {
	//	this.work_amount_RTArray = work_amount_RTArray;
	//	this.work_amount_PAUTArray = work_amount_PAUTArray;
	//	this.work_amount_TOFDArray = work_amount_TOFDArray;
	//	this.work_amount_UTArray = work_amount_UTArray;
	//	this.work_amount_MPTArray = work_amount_MPTArray;
	//	this.work_manpowerArray = work_manpowerArray;
	//	this.work_xrayArray = work_xrayArray;
	//	this.work_PAUTArray = work_PAUTArray;
	//	this.work_charyangArray = work_charyangArray;
	//	}
	
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

	public String getLogin_work_area() {
		return login_work_area;
	}

	public void setLogin_work_area(String login_work_area) {
		this.login_work_area = login_work_area;
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

	public int getWork_manpower_total() {
		return work_manpower_total;
	}

	public void setWork_manpower_total(int work_manpower_total) {
		this.work_manpower_total = work_manpower_total;
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
	
}

