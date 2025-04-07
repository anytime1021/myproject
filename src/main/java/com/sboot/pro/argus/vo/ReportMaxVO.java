package com.sboot.pro.argus.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("reportMaxVO")
@Data
public class ReportMaxVO {
	private int max_RT;
	private int max_PAUT;
	private int max_TOFD;
	private int max_UT;
	private int max_MPT;
	private int max_manpower;
	public int getMax_RT() {
		return max_RT;
	}
	public void setMax_RT(int max_RT) {
		this.max_RT = max_RT;
	}
	public int getMax_PAUT() {
		return max_PAUT;
	}
	public void setMax_PAUT(int max_PAUT) {
		this.max_PAUT = max_PAUT;
	}
	public int getMax_TOFD() {
		return max_TOFD;
	}
	public void setMax_TOFD(int max_TOFD) {
		this.max_TOFD = max_TOFD;
	}
	public int getMax_UT() {
		return max_UT;
	}
	public void setMax_UT(int max_UT) {
		this.max_UT = max_UT;
	}
	public int getMax_MPT() {
		return max_MPT;
	}
	public void setMax_MPT(int max_MPT) {
		this.max_MPT = max_MPT;
	}
	public int getMax_manpower() {
		return max_manpower;
	}
	public void setMax_manpower(int max_manpower) {
		this.max_manpower = max_manpower;
	}
}
