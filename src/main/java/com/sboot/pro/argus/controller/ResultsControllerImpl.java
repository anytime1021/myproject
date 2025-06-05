package com.sboot.pro.argus.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.dao.ResultsDAO;
import com.sboot.pro.argus.service.CommonService;
import com.sboot.pro.argus.service.ResultsService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ReportVO;
import com.sboot.pro.argus.vo.ResultsVO;
import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("resultsController")
public class ResultsControllerImpl implements ResultsController {
	
	@Autowired
	private ReportDAO reportDAO;

	@Autowired
	private ResultsService resultsService;

	@Autowired
	private ResultsDAO resultsDAO;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CommonDAO commonDAO;
	
	// 게시판 접속
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
	
	// 실적 추가 폼
	@Override
	@GetMapping("/results/addResultsForm.do")
	public ModelAndView addResultsForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/results/addResultsForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<ReportVO> fmonth_list = reportDAO.selectFmonth(searchArea);
		mav.addObject("fmonth_list", fmonth_list);
		return mav;	
	}
	
	// 실적 추가(정보저장)
	@Override
	@PostMapping("/results/addResults.do")
	public ModelAndView addResults(@RequestParam(value = "fmonth_name", required = false) String[] fmonth_nameArray,
			@RequestParam(value = "fmonth_profits", required = false) String[] fmonth_profitsArray,
			@RequestParam(value = "results_dailyprofits", required = false) String[] results_dailyprofitsArray,
			@RequestParam(value = "note", required = false) String[] noteArray,
			@RequestParam("work_date") String work_date,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/results/resultsBoard.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<ResultsVO> addResultsList = new ArrayList<>();
		for (int i = 0; i < fmonth_nameArray.length; i++) {
			ResultsVO resultsVO = new ResultsVO();
			resultsVO.setFmonth_name(fmonth_nameArray[i]);
			resultsVO.setFmonth_profits(new BigDecimal(fmonth_profitsArray[i]));
			resultsVO.setResults_dailyprofits(safeParseDecimal(results_dailyprofitsArray[i]));
			resultsVO.setNote(noteArray[i]);
			addResultsList.add(resultsVO);
		}
		
		int listResult = resultsService.addResultsList(searchArea, work_date, addResultsList);
		return mav;
	}
	
	private BigDecimal safeParseDecimal(String str) {
		if (str == null || str.trim().equals("")) {
			return BigDecimal.ZERO;
		}
		try {
			return new BigDecimal(str);
		} catch (NumberFormatException e) {
			return BigDecimal.ZERO;
		}
	}
	
	// 실적 보기
	@Override
	@GetMapping("/results/resultsView.do")
	public ModelAndView resultsView(@RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<ResultsVO> resultsList = new ArrayList<>();
		resultsList = resultsService.selectResultsList(searchArea, work_date);
		
		mav.addObject("resultsList", resultsList);
		
		ResultsVO resultsSum = new ResultsVO();
		resultsSum = resultsService.selectResultsSum(searchArea, work_date);
		
		mav.addObject("resultsSum", resultsSum);
		mav.addObject("work_date", work_date);
		return mav;
	}
}
