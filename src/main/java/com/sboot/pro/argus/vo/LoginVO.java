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
	private int login_grade;
	private int login_area_num;
	
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
	public int getLogin_grade() {
		return login_grade;
	}
	public void setLogin_grade(int login_grade) {
		this.login_grade = login_grade;
	}
	public int getLogin_area_num() {
		return login_area_num;
	}
	public void setLogin_area_num(int login_area_num) {
		this.login_area_num = login_area_num;
	}
}
