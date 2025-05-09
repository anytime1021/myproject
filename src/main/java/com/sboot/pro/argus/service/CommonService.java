package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

public interface CommonService {
	// 게시판 접속 공용
	public List<WorkingDailyBaseVO> reportListTotalJava(String searchArea, String tableName) throws Exception;
	
}
