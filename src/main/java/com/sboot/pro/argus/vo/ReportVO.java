package com.sboot.pro.argus.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("reportVO")
@Data
public class ReportVO {
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
	
	public int getwork_num() {
		return work_num;
	}
	public void setwork_num(int work_num) {
		this.work_num = work_num;
	}
	public String getwork_name() {
		return work_name;
	}
	public void setwork_name(String work_name) {
		this.work_name = work_name;
	}
	public int getwork_amount_RT() {
		return work_amount_RT;
	}
	public void setwork_amount_RT(int work_amount_RT) {
		this.work_amount_RT = work_amount_RT;
	}
	public int getwork_amount_PAUT() {
		return work_amount_PAUT;
	}
	public void setwork_amount_PAUT(int work_amount_PAUT) {
		this.work_amount_PAUT = work_amount_PAUT;
	}
	public int getwork_amount_TOFD() {
		return work_amount_TOFD;
	}
	public void setwork_amount_TOFD(int work_amount_TOFD) {
		this.work_amount_TOFD = work_amount_TOFD;
	}
	public int getwork_amount_UT() {
		return work_amount_UT;
	}
	public void setwork_amount_UT(int work_amount_UT) {
		this.work_amount_UT = work_amount_UT;
	}
	public int getwork_amount_MPT() {
		return work_amount_MPT;
	}
	public void setwork_amount_MPT(int work_amount_MPT) {
		this.work_amount_MPT = work_amount_MPT;
	}
	public int getwork_manpower() {
		return work_manpower;
	}
	public void setwork_manpower(int work_manpower) {
		this.work_manpower = work_manpower;
	}
	public String getwork_xray() {
		return work_xray;
	}
	public void setwork_xray(String work_xray) {
		this.work_xray = work_xray;
	}
	public String getwork_PAUT() {
		return work_PAUT;
	}
	public void setwork_PAUT(String work_PAUT) {
		this.work_PAUT = work_PAUT;
	}
	public String getwork_charyang() {
		return work_charyang;
	}
	public void setwork_charyang(String work_charyang) {
		this.work_charyang = work_charyang;
	}
	public String getLogin_work_area() {
		return login_work_area;
	}
	public void setLogin_work_area(String login_work_area) {
		this.login_work_area = login_work_area;
	}
}

