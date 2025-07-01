package com.sboot.pro.argus.vo;

import org.springframework.web.multipart.MultipartFile;

public class BlockVO extends WorkingDailyBaseVO {
	
	// 시험편 정보
	private int df_num;
	private String df_idNumber;
	private MultipartFile df_picture;
	private String df_pictureName;
	private String df_material;
	private String df_size;
	private String df_usage;
	private String df_form;
	private String df_defectType;
	private String df_manufacture;
	private String df_itemStatus;
	private String df_moveStatus;
	
	// 이동 기록
	private int mr_num;
	private String mr_moveDate;
	private String mr_rentalArea;
	public int getDf_num() {
		return df_num;
	}
	public void setDf_num(int df_num) {
		this.df_num = df_num;
	}
	public String getDf_idNumber() {
		return df_idNumber;
	}
	public void setDf_idNumber(String df_idNumber) {
		this.df_idNumber = df_idNumber;
	}
	public MultipartFile getDf_picture() {
		return df_picture;
	}
	public void setDf_picture(MultipartFile df_picture) {
		this.df_picture = df_picture;
	}
	public String getDf_pictureName() {
		return df_pictureName;
	}
	public void setDf_pictureName(String df_pictureName) {
		this.df_pictureName = df_pictureName;
	}
	public String getDf_material() {
		return df_material;
	}
	public void setDf_material(String df_material) {
		this.df_material = df_material;
	}
	public String getDf_size() {
		return df_size;
	}
	public void setDf_size(String df_size) {
		this.df_size = df_size;
	}
	public String getDf_usage() {
		return df_usage;
	}
	public void setDf_usage(String df_usage) {
		this.df_usage = df_usage;
	}
	public String getDf_form() {
		return df_form;
	}
	public void setDf_form(String df_form) {
		this.df_form = df_form;
	}
	public String getDf_defectType() {
		return df_defectType;
	}
	public void setDf_defectType(String df_defectType) {
		this.df_defectType = df_defectType;
	}
	public String getDf_manufacture() {
		return df_manufacture;
	}
	public void setDf_manufacture(String df_manufacture) {
		this.df_manufacture = df_manufacture;
	}
	public String getDf_itemStatus() {
		return df_itemStatus;
	}
	public void setDf_itemStatus(String df_itemStatus) {
		this.df_itemStatus = df_itemStatus;
	}
	public String getDf_moveStatus() {
		return df_moveStatus;
	}
	public void setDf_moveStatus(String df_moveStatus) {
		this.df_moveStatus = df_moveStatus;
	}
	public int getMr_num() {
		return mr_num;
	}
	public void setMr_num(int mr_num) {
		this.mr_num = mr_num;
	}
	public String getMr_moveDate() {
		return mr_moveDate;
	}
	public void setMr_moveDate(String mr_moveDate) {
		this.mr_moveDate = mr_moveDate;
	}
	public String getMr_rentalArea() {
		return mr_rentalArea;
	}
	public void setMr_rentalArea(String mr_rentalArea) {
		this.mr_rentalArea = mr_rentalArea;
	}
	
	@Override
	public String toString() {
		return "BlockVO [df_num=" + df_num + ", df_idNumber=" + df_idNumber + ", df_picture=" + df_picture
				+ ", df_material=" + df_material + ", df_size=" + df_size + ", df_usage=" + df_usage + ", df_form="
				+ df_form + ", df_defectType=" + df_defectType + ", df_manufacture=" + df_manufacture
				+ ", df_itemStatus=" + df_itemStatus + ", df_moveStatus=" + df_moveStatus + ", mr_num=" + mr_num
				+ ", mr_moveDate=" + mr_moveDate + ", mr_rentalArea=" + mr_rentalArea + "]";
	}
}
