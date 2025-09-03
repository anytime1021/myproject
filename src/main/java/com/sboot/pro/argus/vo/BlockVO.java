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
	private int moveList_num;
	private String moveList_lender;
	private String moveList_lender_rank;
	private String moveList_recipient;
	private String moveList_recipient_area;
	private String moveList_recipient_rank;
	private String moveList_rental_date;
	private String moveList_return_date;
	private String moveList_moveStatus;
	private String created_at;
	
	// 승인 대기
	private int app_num;
	private String app_branch_status;
	private String app_branch_comment;
	private String app_branch_area;
	private String app_head_status;
	private String app_head_comment;
	 
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
	public int getMoveList_num() {
		return moveList_num;
	}
	public void setMoveList_num(int moveList_num) {
		this.moveList_num = moveList_num;
	}
	public String getMoveList_lender() {
		return moveList_lender;
	}
	public void setMoveList_lender(String moveList_lender) {
		this.moveList_lender = moveList_lender;
	}
	public String getMoveList_lender_rank() {
		return moveList_lender_rank;
	}
	public void setMoveList_lender_rank(String moveList_lender_rank) {
		this.moveList_lender_rank = moveList_lender_rank;
	}
	public String getMoveList_recipient() {
		return moveList_recipient;
	}
	public void setMoveList_recipient(String moveList_recipient) {
		this.moveList_recipient = moveList_recipient;
	}
	public String getMoveList_recipient_area() {
		return moveList_recipient_area;
	}
	public void setMoveList_recipient_area(String moveList_recipient_area) {
		this.moveList_recipient_area = moveList_recipient_area;
	}
	public String getMoveList_recipient_rank() {
		return moveList_recipient_rank;
	}
	public void setMoveList_recipient_rank(String moveList_recipient_rank) {
		this.moveList_recipient_rank = moveList_recipient_rank;
	}
	public String getMoveList_rental_date() {
		return moveList_rental_date;
	}
	public void setMoveList_rental_date(String moveList_rental_date) {
		this.moveList_rental_date = moveList_rental_date;
	}
	public String getMoveList_return_date() {
		return moveList_return_date;
	}
	public void setMoveList_return_date(String moveList_return_date) {
		this.moveList_return_date = moveList_return_date;
	}
	public String getMoveList_moveStatus() {
		return moveList_moveStatus;
	}
	public void setMoveList_moveStatus(String moveList_moveStatus) {
		this.moveList_moveStatus = moveList_moveStatus;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public int getApp_num() {
		return app_num;
	}
	public void setApp_num(int app_num) {
		this.app_num = app_num;
	}
	public String getApp_branch_status() {
		return app_branch_status;
	}
	public void setApp_branch_status(String app_branch_status) {
		this.app_branch_status = app_branch_status;
	}
	public String getApp_branch_comment() {
		return app_branch_comment;
	}
	public void setApp_branch_comment(String app_branch_comment) {
		this.app_branch_comment = app_branch_comment;
	}
	public String getApp_branch_area() {
		return app_branch_area;
	}
	public void setApp_branch_area(String app_branch_area) {
		this.app_branch_area = app_branch_area;
	}
	public String getApp_head_status() {
		return app_head_status;
	}
	public void setApp_head_status(String app_head_status) {
		this.app_head_status = app_head_status;
	}
	public String getApp_head_comment() {
		return app_head_comment;
	}
	public void setApp_head_comment(String app_head_comment) {
		this.app_head_comment = app_head_comment;
	}
	
	@Override
	public String toString() {
		return "BlockVO [df_num=" + df_num + ", df_idNumber=" + df_idNumber + ", df_picture=" + df_picture
				+ ", df_pictureName=" + df_pictureName + ", df_material=" + df_material + ", df_size=" + df_size
				+ ", df_usage=" + df_usage + ", df_form=" + df_form + ", df_defectType=" + df_defectType
				+ ", df_manufacture=" + df_manufacture + ", df_itemStatus=" + df_itemStatus + ", df_moveStatus="
				+ df_moveStatus + ", moveList_num=" + moveList_num + ", moveList_lender=" + moveList_lender
				+ ", moveList_lender_rank=" + moveList_lender_rank + ", moveList_recipient=" + moveList_recipient
				+ ", moveList_recipient_area=" + moveList_recipient_area + ", moveList_recipient_rank="
				+ moveList_recipient_rank + ", moveList_rental_date=" + moveList_rental_date + ", moveList_return_date="
				+ moveList_return_date + ", moveList_moveStatus=" + moveList_moveStatus + ", created_at=" + created_at
				+ "]";
	}
}
