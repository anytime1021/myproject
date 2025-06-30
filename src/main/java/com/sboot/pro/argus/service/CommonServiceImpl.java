package com.sboot.pro.argus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

@Service("commonService")
@Transactional(propagation = Propagation.REQUIRED)
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	private CommonDAO commonDAO;
	
	// 게시판 접속 공용
	@Override
	public List<WorkingDailyBaseVO> reportListTotalJava(String searchArea, String tableName, int offset, int limit) throws Exception {
			return commonDAO.selectTotalReportList(searchArea, tableName, offset, limit);
	}
	
	// 게시판 페이징 카운트
	@Override
	public int getReportCount(String searchArea, String tableName) throws Exception {
		return commonDAO.selectReportCount(searchArea, tableName);
	}
	
}
