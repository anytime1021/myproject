package com.sboot.pro.argus.vo;


import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("reportVO")
@Data
public class ReportVO extends WorkingDailyBaseVO {
	
	// 일별 데이터
	private int work_num;
	private String work_name;
	private int work_amount_HTW1;
	private int work_amount_HTW2;
	private int work_amount_HTW3;
	private int work_amount_HTW4;
	private int work_amount_HTW5;
	private int work_manpower;
	private String work_xray;
	private String work_PAUT;
	private String work_charyang;

	private String work_title;
	private long work_price;
	private String update_date;
	
	// 월별 데이터
	private String work_name_total;
	private String work_xray_total;
	private String work_PAUT_total;
	private String work_charyang_total;
	private String login_work_area_total;
	private String work_title_total;
	private String work_date_total;
	
	// 변수 배열
//	private int work_amount_HTW1Array;
//	private int work_amount_HTW2Array;
//	private int work_amount_HTW3rray;
//	private int work_amount_HTW4Array;
//	private int work_amount_HTW5Array;
//	private int work_manpowerArray;
//	private String work_xrayArray;
//	private String work_PAUTArray;
//	private String work_charyangArray;
	
	// 전체 고정값
	private int work_num_total;
	private int work_amount_HTW1_total;
	private int work_amount_HTW2_total;
	private int work_amount_HTW3_total;
	private int work_amount_HTW4_total;
	private int work_amount_HTW5_total;
	private int work_manpower_total;
	
	// 단가
	private int unitcost_num;
	private String unitcost_method;
	private String unitcost_unitQuantity;
	private BigDecimal unitcost_cost;

	// 작업 방식
	private int htw_num;
	private String htw_htw1;
	private String htw_htw2;
	private String htw_htw3;
	private String htw_htw4;
	private String htw_htw5;

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
	public int getWork_amount_HTW1() {
		return work_amount_HTW1;
	}
	public void setWork_amount_HTW1(int work_amount_HTW1) {
		this.work_amount_HTW1 = work_amount_HTW1;
	}
	public int getWork_amount_HTW2() {
		return work_amount_HTW2;
	}
	public void setWork_amount_HTW2(int work_amount_HTW2) {
		this.work_amount_HTW2 = work_amount_HTW2;
	}
	public int getWork_amount_HTW3() {
		return work_amount_HTW3;
	}
	public void setWork_amount_HTW3(int work_amount_HTW3) {
		this.work_amount_HTW3 = work_amount_HTW3;
	}
	public int getWork_amount_HTW4() {
		return work_amount_HTW4;
	}
	public void setWork_amount_HTW4(int work_amount_HTW4) {
		this.work_amount_HTW4 = work_amount_HTW4;
	}
	public int getWork_amount_HTW5() {
		return work_amount_HTW5;
	}
	public void setWork_amount_HTW5(int work_amount_HTW5) {
		this.work_amount_HTW5 = work_amount_HTW5;
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
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
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
	public String getWork_date_total() {
		return work_date_total;
	}
	public void setWork_date_total(String work_date_total) {
		this.work_date_total = work_date_total;
	}
//	public int getWork_amount_HTW1Array() {
//		return work_amount_HTW1Array;
//	}
//	public void setWork_amount_HTW1Array(int work_amount_HTW1Array) {
//		this.work_amount_HTW1Array = work_amount_HTW1Array;
//	}
//	public int getWork_amount_HTW2Array() {
//		return work_amount_HTW2Array;
//	}
//	public void setWork_amount_HTW2Array(int work_amount_HTW2Array) {
//		this.work_amount_HTW2Array = work_amount_HTW2Array;
//	}
//	public int getWork_amount_HTW3rray() {
//		return work_amount_HTW3rray;
//	}
//	public void setWork_amount_HTW3rray(int work_amount_HTW3rray) {
//		this.work_amount_HTW3rray = work_amount_HTW3rray;
//	}
//	public int getWork_amount_HTW4Array() {
//		return work_amount_HTW4Array;
//	}
//	public void setWork_amount_HTW4Array(int work_amount_HTW4Array) {
//		this.work_amount_HTW4Array = work_amount_HTW4Array;
//	}
//	public int getWork_amount_HTW5Array() {
//		return work_amount_HTW5Array;
//	}
//	public void setWork_amount_HTW5Array(int work_amount_HTW5Array) {
//		this.work_amount_HTW5Array = work_amount_HTW5Array;
//	}
//	public int getWork_manpowerArray() {
//		return work_manpowerArray;
//	}
//	public void setWork_manpowerArray(int work_manpowerArray) {
//		this.work_manpowerArray = work_manpowerArray;
//	}
//	public String getWork_xrayArray() {
//		return work_xrayArray;
//	}
//	public void setWork_xrayArray(String work_xrayArray) {
//		this.work_xrayArray = work_xrayArray;
//	}
//	public String getWork_PAUTArray() {
//		return work_PAUTArray;
//	}
//	public void setWork_PAUTArray(String work_PAUTArray) {
//		this.work_PAUTArray = work_PAUTArray;
//	}
//	public String getWork_charyangArray() {
//		return work_charyangArray;
//	}
//	public void setWork_charyangArray(String work_charyangArray) {
//		this.work_charyangArray = work_charyangArray;
//	}
	public int getWork_num_total() {
		return work_num_total;
	}
	public void setWork_num_total(int work_num_total) {
		this.work_num_total = work_num_total;
	}
	public int getWork_amount_HTW1_total() {
		return work_amount_HTW1_total;
	}
	public void setWork_amount_HTW1_total(int work_amount_HTW1_total) {
		this.work_amount_HTW1_total = work_amount_HTW1_total;
	}
	public int getWork_amount_HTW2_total() {
		return work_amount_HTW2_total;
	}
	public void setWork_amount_HTW2_total(int work_amount_HTW2_total) {
		this.work_amount_HTW2_total = work_amount_HTW2_total;
	}
	public int getWork_amount_HTW3_total() {
		return work_amount_HTW3_total;
	}
	public void setWork_amount_HTW3_total(int work_amount_HTW3_total) {
		this.work_amount_HTW3_total = work_amount_HTW3_total;
	}
	public int getWork_amount_HTW4_total() {
		return work_amount_HTW4_total;
	}
	public void setWork_amount_HTW4_total(int work_amount_HTW4_total) {
		this.work_amount_HTW4_total = work_amount_HTW4_total;
	}
	public int getWork_amount_HTW5_total() {
		return work_amount_HTW5_total;
	}
	public void setWork_amount_HTW5_total(int work_amount_HTW5_total) {
		this.work_amount_HTW5_total = work_amount_HTW5_total;
	}
	public int getWork_manpower_total() {
		return work_manpower_total;
	}
	public void setWork_manpower_total(int work_manpower_total) {
		this.work_manpower_total = work_manpower_total;
	}
	public int getUnitcost_num() {
		return unitcost_num;
	}
	public void setUnitcost_num(int unitcost_num) {
		this.unitcost_num = unitcost_num;
	}
	public String getUnitcost_method() {
		return unitcost_method;
	}
	public void setUnitcost_method(String unitcost_method) {
		this.unitcost_method = unitcost_method;
	}
	public String getUnitcost_unitQuantity() {
		return unitcost_unitQuantity;
	}
	public void setUnitcost_unitQuantity(String unitcost_unitQuantity) {
		this.unitcost_unitQuantity = unitcost_unitQuantity;
	}
	public BigDecimal getUnitcost_cost() {
		return unitcost_cost;
	}
	public void setUnitcost_cost(BigDecimal unitcost_cost) {
		this.unitcost_cost = unitcost_cost;
	}
	public int getHtw_num() {
		return htw_num;
	}
	public void setHtw_num(int htw_num) {
		this.htw_num = htw_num;
	}
	public String getHtw_htw1() {
		return htw_htw1;
	}
	public void setHtw_htw1(String htw_htw1) {
		this.htw_htw1 = htw_htw1;
	}
	public String getHtw_htw2() {
		return htw_htw2;
	}
	public void setHtw_htw2(String htw_htw2) {
		this.htw_htw2 = htw_htw2;
	}
	public String getHtw_htw3() {
		return htw_htw3;
	}
	public void setHtw_htw3(String htw_htw3) {
		this.htw_htw3 = htw_htw3;
	}
	public String getHtw_htw4() {
		return htw_htw4;
	}
	public void setHtw_htw4(String htw_htw4) {
		this.htw_htw4 = htw_htw4;
	}
	public String getHtw_htw5() {
		return htw_htw5;
	}
	public void setHtw_htw5(String htw_htw5) {
		this.htw_htw5 = htw_htw5;
	}
}


