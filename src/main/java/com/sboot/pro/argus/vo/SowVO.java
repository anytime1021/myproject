package com.sboot.pro.argus.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("sowVO")
@Data
public class SowVO extends WorkingDailyBaseVO {
	// sow
	private int sowDWL_num;
	private String sowDWL_name;
	private String sowDWL_work_name;
	private String sowDWL_shift;
	private int sowDWL_hours;
	private int sowDWL_overtime;
	
	// 직원
	private int emp_num;
	private String emp_name;
	private String emp_position;
	private String emp_resignation_date;

	
	public int getSowDWL_num() {
		return sowDWL_num;
	}
	public void setSowDWL_num(int sowDWL_num) {
		this.sowDWL_num = sowDWL_num;
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
	public int getEmp_num() {
		return emp_num;
	}
	public void setEmp_num(int emp_num) {
		this.emp_num = emp_num;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_position() {
		return emp_position;
	}
	public void setEmp_position(String emp_position) {
		this.emp_position = emp_position;
	}
	public String getEmp_resignation_date() {
		return emp_resignation_date;
	}
	public void setEmp_resignation_date(String emp_resignation_date) {
		this.emp_resignation_date = emp_resignation_date;
	}
	
	@Override
	public String toString() {
		return "SowVO [sowDWL_name=" + sowDWL_name + ", sowDWL_work_name=" + sowDWL_work_name + ", sowDWL_shift="
				+ sowDWL_shift + ", sowDWL_hours=" + sowDWL_hours + ", sowDWL_overtime=" + sowDWL_overtime
				+ ", emp_num=" + emp_num + ", emp_name=" + emp_name + ", emp_position=" + emp_position
				+ ", emp_resignation_date=" + emp_resignation_date + "]";
	}
}
