package com.sboot.pro.argus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sboot.pro.argus.dao.ResultsDAO;
import com.sboot.pro.argus.vo.ResultsVO;

@Service("resultsService")
@Transactional(propagation = Propagation.REQUIRED)
public class ResultsServiceImpl implements ResultsService {
	
	@Autowired
	ResultsDAO resultsDAO;
	
	public int addResultsList(String searchArea, String work_date, List<ResultsVO> addResultsList) throws Exception {
		return resultsDAO.insertResultsList(searchArea, work_date, addResultsList);
	}
	
	// 실적 보기
	public List<ResultsVO> selectResultsList(String searchArea, String work_date) throws Exception {
		String start_date = work_date.substring(0,7) + "-01";
		return resultsDAO.selectResultsList(searchArea, start_date, work_date);
	}
	
	public ResultsVO selectResultsSum(String searchArea, String work_date) throws Exception {
		String start_date = work_date.substring(0,7) + "-01";
		return resultsDAO.selectResultsSum(searchArea, start_date, work_date);
	}
	
	// mixed

	public int modResultsList(String searchArea, String login_id, String work_date, List<ResultsVO> modResultsList) throws Exception {
		int results = resultsDAO.insertResultsUpdateLog(searchArea, login_id, work_date);
		return resultsDAO.updateResultsList(searchArea, work_date, modResultsList);
	}
}
