package com.sboot.pro.argus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.dao.ResultsDAO;
import com.sboot.pro.argus.service.CommonService;
import com.sboot.pro.argus.service.ResultsService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ResultsVO;
import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("resultsController")
public class ResultsControllerImpl implements ResultsController {

	@Autowired
	private ResultsService resultsService;

	@Autowired
	private ResultsDAO resultsDAO;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Override
	@GetMapping("/results/resultsBoard.do")
	public ModelAndView resultsBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/results/resultsBoard");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int token = 3;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<WorkingDailyBaseVO> resultsBoardList = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("resultsBoardList", resultsBoardList);
		return mav;
	}
	
	@GetMapping("/results/addResultsForm.do")
	public ModelAndView addResultsForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/results/addResultsForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		ResultsVO addResultsForm = new ResultsVO();
//		addResultsForm = resultsService.addResultsForm("searchArea");
		return mav;
	}
}
