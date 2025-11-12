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
	private String df_weld;
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
	
	// 시험편 이동 보고서 - 부록3
	private int app_num;
	private String app_hnd_comment;
	private String app_hnd_area;
	private String app_hnd_name;
	private String app_hnd_create_at;
	private String app_hnd_transMethod;
	private String app_rcv_area;
	private String app_rcv_name;
	private String app_rcv_create_at;
	private String app_isError;
	private int app_rank;
	private String app_rcv_status;
	private String app_head_status;
	private String app_type;
	private int expSign_num;
	private String expSign_name;
	private MultipartFile expertSignName;
	
	// 시험편 반출 관련
	private String app_period;
	private String returnRequest;
	
	// 블럭 스펙 업로드
	private int bs_num;
	private String file_name;
	private String file_path;
	private String upload_date;
	
	// 블럭 점검 게시판
	private int bib_num;
	private String bib_title;
	private String bib_date;
	
	// 블럭 점검 리스트
	private int bil_num;
	private String bil_status;
	private String bil_date;
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
	public String getDf_weld() {
		return df_weld;
	}
	public void setDf_weld(String df_weld) {
		this.df_weld = df_weld;
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
	public String getApp_hnd_comment() {
		return app_hnd_comment;
	}
	public void setApp_hnd_comment(String app_hnd_comment) {
		this.app_hnd_comment = app_hnd_comment;
	}
	public String getApp_hnd_area() {
		return app_hnd_area;
	}
	public void setApp_hnd_area(String app_hnd_area) {
		this.app_hnd_area = app_hnd_area;
	}
	public String getApp_hnd_name() {
		return app_hnd_name;
	}
	public void setApp_hnd_name(String app_hnd_name) {
		this.app_hnd_name = app_hnd_name;
	}
	public String getApp_hnd_create_at() {
		return app_hnd_create_at;
	}
	public void setApp_hnd_create_at(String app_hnd_create_at) {
		this.app_hnd_create_at = app_hnd_create_at;
	}
	public String getApp_hnd_transMethod() {
		return app_hnd_transMethod;
	}
	public void setApp_hnd_transMethod(String app_hnd_transMethod) {
		this.app_hnd_transMethod = app_hnd_transMethod;
	}
	public String getApp_rcv_area() {
		return app_rcv_area;
	}
	public void setApp_rcv_area(String app_rcv_area) {
		this.app_rcv_area = app_rcv_area;
	}
	public String getApp_rcv_name() {
		return app_rcv_name;
	}
	public void setApp_rcv_name(String app_rcv_name) {
		this.app_rcv_name = app_rcv_name;
	}
	public String getApp_rcv_create_at() {
		return app_rcv_create_at;
	}
	public void setApp_rcv_create_at(String app_rcv_create_at) {
		this.app_rcv_create_at = app_rcv_create_at;
	}
	public String getApp_isError() {
		return app_isError;
	}
	public void setApp_isError(String app_isError) {
		this.app_isError = app_isError;
	}
	public int getApp_rank() {
		return app_rank;
	}
	public void setApp_rank(int app_rank) {
		this.app_rank = app_rank;
	}
	public String getApp_rcv_status() {
		return app_rcv_status;
	}
	public void setApp_rcv_status(String app_rcv_status) {
		this.app_rcv_status = app_rcv_status;
	}
	public String getApp_head_status() {
		return app_head_status;
	}
	public void setApp_head_status(String app_head_status) {
		this.app_head_status = app_head_status;
	}
	public String getApp_type() {
		return app_type;
	}
	public void setApp_type(String app_type) {
		this.app_type = app_type;
	}
	public int getExpSign_num() {
		return expSign_num;
	}
	public void setExpSign_num(int expSign_num) {
		this.expSign_num = expSign_num;
	}
	public String getExpSign_name() {
		return expSign_name;
	}
	public void setExpSign_name(String expSign_name) {
		this.expSign_name = expSign_name;
	}
	public MultipartFile getExpertSignName() {
		return expertSignName;
	}
	public void setExpertSignName(MultipartFile expertSignName) {
		this.expertSignName = expertSignName;
	}
	public String getApp_period() {
		return app_period;
	}
	public void setApp_period(String app_period) {
		this.app_period = app_period;
	}
	public String getReturnRequest() {
		return returnRequest;
	}
	public void setReturnRequest(String returnRequest) {
		this.returnRequest = returnRequest;
	}
	public int getBs_num() {
		return bs_num;
	}
	public void setBs_num(int bs_num) {
		this.bs_num = bs_num;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public int getBib_num() {
		return bib_num;
	}
	public void setBib_num(int bib_num) {
		this.bib_num = bib_num;
	}
	public String getBib_title() {
		return bib_title;
	}
	public void setBib_title(String bib_title) {
		this.bib_title = bib_title;
	}
	public String getBib_date() {
		return bib_date;
	}
	public void setBib_date(String bib_date) {
		this.bib_date = bib_date;
	}
	public int getBil_num() {
		return bil_num;
	}
	public void setBil_num(int bil_num) {
		this.bil_num = bil_num;
	}
	public String getBil_status() {
		return bil_status;
	}
	public void setBil_status(String bil_status) {
		this.bil_status = bil_status;
	}
	public String getBil_date() {
		return bil_date;
	}
	public void setBil_date(String bil_date) {
		this.bil_date = bil_date;
	}
	
	@Override
	public String toString() {
		return "BlockVO [df_num=" + df_num + ", df_idNumber=" + df_idNumber + ", df_picture=" + df_picture
				+ ", df_pictureName=" + df_pictureName + ", df_material=" + df_material + ", df_size=" + df_size
				+ ", df_usage=" + df_usage + ", df_weld=" + df_weld + ", df_defectType=" + df_defectType
				+ ", df_manufacture=" + df_manufacture + ", df_itemStatus=" + df_itemStatus + ", df_moveStatus="
				+ df_moveStatus + ", moveList_num=" + moveList_num + ", moveList_lender=" + moveList_lender
				+ ", moveList_lender_rank=" + moveList_lender_rank + ", moveList_recipient=" + moveList_recipient
				+ ", moveList_recipient_area=" + moveList_recipient_area + ", moveList_recipient_rank="
				+ moveList_recipient_rank + ", moveList_rental_date=" + moveList_rental_date + ", moveList_return_date="
				+ moveList_return_date + ", moveList_moveStatus=" + moveList_moveStatus + ", created_at=" + created_at
				+ ", app_num=" + app_num + ", app_hnd_comment=" + app_hnd_comment + ", app_hnd_area=" + app_hnd_area
				+ ", app_hnd_name=" + app_hnd_name + ", app_hnd_create_at=" + app_hnd_create_at
				+ ", app_hnd_transMethod=" + app_hnd_transMethod + ", app_rcv_area=" + app_rcv_area + ", app_rcv_name="
				+ app_rcv_name + ", app_rcv_create_at=" + app_rcv_create_at + ", app_isError=" + app_isError
				+ ", app_rank=" + app_rank + ", app_rcv_status=" + app_rcv_status + ", app_head_status="
				+ app_head_status + ", bs_num=" + bs_num + ", file_name=" + file_name + ", file_path=" + file_path
				+ ", upload_date=" + upload_date + ", bib_num=" + bib_num + ", bib_title=" + bib_title + ", bib_date="
				+ bib_date + ", bil_num=" + bil_num + ", bil_status=" + bil_status + ", bil_date=" + bil_date + "]";
	}
}
