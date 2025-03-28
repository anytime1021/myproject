package com.sboot.pro.argus.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sboot.pro.argus.vo.LoginVO;

@Mapper
@Repository("loginDAO")
public interface LoginDAO {
	public LoginVO login(@Param("login_id") String login_id, @Param("login_pwd") String login_pwd) throws Exception;
}
