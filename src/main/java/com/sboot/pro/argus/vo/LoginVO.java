package com.sboot.pro.argus.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component("loginVO")
@Data
public class LoginVO {
	private int login_num;
	private String login_id;
	private String login_pwd;
	private String login_area;
	private String login_name;
	private int login_report_access;
	private int login_block_access;
	private String login_position;
	private String login_department;
	
	public int getLogin_num() {
		return login_num;
	}
	public void setLogin_num(int login_num) {
		this.login_num = login_num;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_pwd() {
		return login_pwd;
	}
	public void setLogin_pwd(String login_pwd) {
		this.login_pwd = login_pwd;
	}
	public String getLogin_area() {
		return login_area;
	}
	public void setLogin_area(String login_area) {
		this.login_area = login_area;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public int getLogin_report_access() {
		return login_report_access;
	}
	public void setLogin_report_access(int login_report_access) {
		this.login_report_access = login_report_access;
	}
	public int getLogin_block_access() {
		return login_block_access;
	}
	public void setLogin_block_access(int login_block_access) {
		this.login_block_access = login_block_access;
	}
	public String getLogin_position() {
		return login_position;
	}
	public void setLogin_position(String login_position) {
		this.login_position = login_position;
	}
	public String getLogin_department() {
		return login_department;
	}
	public void setLogin_department(String login_department) {
		this.login_department = login_department;
	}
}
