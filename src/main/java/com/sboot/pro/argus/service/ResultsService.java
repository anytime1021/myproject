package com.sboot.pro.argus.service;

import java.util.List;

import com.sboot.pro.argus.vo.ResultsVO;

public interface ResultsService {
	// 게시판 접속
	public int addResultsBoard(String searchArea, String work_date) throws Exception;
	
	// 실적 추가 (정보저장)
	public int addResultsList(String searchArea, String work_date, List<ResultsVO> addResultsList) throws Exception;

	// 실적 보기
	public List<ResultsVO> selectResultsList(String searchArea, String work_date) throws Exception;
	public ResultsVO selectResultsSum(String searchArea, String work_date) throws Exception;
	
	
	// mixed
	public int modResultsList(String searchArea, String login_id, String work_date, List<ResultsVO> modResultsList) throws Exception;
}
