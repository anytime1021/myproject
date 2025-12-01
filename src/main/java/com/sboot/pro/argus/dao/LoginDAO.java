package com.sboot.pro.argus.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.LoginVO;

@Mapper
@Repository("loginDAO")
public interface LoginDAO {
	public LoginVO login(@Param("login_id") String login_id, @Param("login_pwd") String login_pwd) throws Exception;
	
	// 마이페이지 
	public LoginVO selectMyPage(String myId) throws Exception;
	
	// 마이페이지 수정
	public void updateMyPage(LoginVO modMyPage) throws Exception;
	
	// 비밀번호 저장
	public int updatePassword(@Param("myId") String myId, @Param("changePassword") String changePassword) throws Exception;

	// 2025-11-26
	
	// 회원가입
	public List<LoginVO> singUpList() throws Exception;
	public int insertSignUp(LoginVO signUp) throws Exception;
	
	// 아이디 붕복 체크
	public boolean checkDuplicate(String login_id) throws Exception;
	
	// 아이디 목록
	public List<LoginVO> memberList() throws Exception;
	
	// 회원 탈퇴
	public int deleteMember(int login_num) throws Exception;
	
	// 사인 찾기
	public String searchSign(String login_department) throws Exception;
}
