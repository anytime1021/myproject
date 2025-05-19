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
	
	// 주간추가 - 야간 - 야간추가 변수
	private int weeklyShiftAdditional;
	private int nightShift;
	private int nightShiftAdditional;
	
	// sow 추가근무 변수
	private int overtime0;
	private int overtime1;
	private int overtime2;
	private int overtime3;
	private int overtime4;
	private int overtime5;
	private int overtime6;
	private int overtime7;
	private int overtime8;
	private int overtime_sum;
	
	// 직원
	private int emp_num;
	private String emp_name;
	private String emp_position;
	private String emp_resignation_date;
	
	// shiftType
	private boolean showTd;
	private boolean hasShift;
	
	// 출장관련
	private String bt_inout;
	
	public boolean isShowTd() {
		return showTd;
	}
	public void setShowTd(boolean showTd) {
		this.showTd = showTd;
	}
	public boolean isHasShift() {
		return hasShift;
	}
	public void setHasShift(boolean hasShift) {
		this.hasShift = hasShift;
	}
	
	
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
	
	public int getWeeklyShiftAdditional() {
		return weeklyShiftAdditional;
	}
	public void setWeeklyShiftAdditional(int weeklyShiftAdditional) {
		this.weeklyShiftAdditional = weeklyShiftAdditional;
	}
	public int getNightShift() {
		return nightShift;
	}
	public void setNightShift(int nightShift) {
		this.nightShift = nightShift;
	}
	public int getNightShiftAdditional() {
		return nightShiftAdditional;
	}
	public void setNightShiftAdditional(int nightShiftAdditional) {
		this.nightShiftAdditional = nightShiftAdditional;
	}
	public int getOvertime0() {
		return overtime0;
	}
	public void setOvertime0(int overtime0) {
		this.overtime0 = overtime0;
	}
	public int getOvertime1() {
		return overtime1;
	}
	public void setOvertime1(int overtime1) {
		this.overtime1 = overtime1;
	}
	public int getOvertime2() {
		return overtime2;
	}
	public void setOvertime2(int overtime2) {
		this.overtime2 = overtime2;
	}
	public int getOvertime3() {
		return overtime3;
	}
	public void setOvertime3(int overtime3) {
		this.overtime3 = overtime3;
	}
	public int getOvertime4() {
		return overtime4;
	}
	public void setOvertime4(int overtime4) {
		this.overtime4 = overtime4;
	}
	public int getOvertime5() {
		return overtime5;
	}
	public void setOvertime5(int overtime5) {
		this.overtime5 = overtime5;
	}
	public int getOvertime6() {
		return overtime6;
	}
	public void setOvertime6(int overtime6) {
		this.overtime6 = overtime6;
	}
	public int getOvertime7() {
		return overtime7;
	}
	public void setOvertime7(int overtime7) {
		this.overtime7 = overtime7;
	}
	public int getOvertime8() {
		return overtime8;
	}
	public void setOvertime8(int overtime8) {
		this.overtime8 = overtime8;
	}
	public int getOvertime_sum() {
		return overtime_sum;
	}
	public void setOvertime_sum(int overtime_sum) {
		this.overtime_sum = overtime_sum;
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
	public String getBt_inout() {
		return bt_inout;
	}
	public void setBt_inout(String bt_inout) {
		this.bt_inout = bt_inout;
	}
	
	@Override
	public String toString() {
		return "SowVO [sowDWL_num=" + sowDWL_num + ", sowDWL_name=" + sowDWL_name + ", sowDWL_work_name="
				+ sowDWL_work_name + ", sowDWL_shift=" + sowDWL_shift + ", sowDWL_hours=" + sowDWL_hours
				+ ", sowDWL_overtime=" + sowDWL_overtime + ", weeklyShiftAdditional=" + weeklyShiftAdditional
				+ ", nightShift=" + nightShift + ", nightShiftAdditional=" + nightShiftAdditional + ", overtime0="
				+ overtime0 + ", overtime1=" + overtime1 + ", overtime2=" + overtime2 + ", overtime3=" + overtime3
				+ ", overtime4=" + overtime4 + ", overtime5=" + overtime5 + ", overtime6=" + overtime6 + ", overtime7="
				+ overtime7 + ", overtime8=" + overtime8 + ", overtime_sum=" + overtime_sum + ", emp_num=" + emp_num
				+ ", emp_name=" + emp_name + ", emp_position=" + emp_position + ", emp_resignation_date="
				+ emp_resignation_date + "]";
	}
}
