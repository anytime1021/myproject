package com.sboot.pro.argus.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.DTO.DailyReportWorkrate;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.service.ReportService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("reportController")
public class ReportControllerImpl implements ReportController{
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportVO reportVO;
	
	@Autowired
	private ReportDAO reportDAO;
	
	
	// 일별 보고서 게시판 접속
	@Override
	@GetMapping("/report/reportArea.do")
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportArea");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
//		List<ReportVO> reportListJsp = new ArrayList<ReportVO>();
//		reportListJsp = reportService.reportListJava(searchArea);
//		 Timestamp workDate = (Timestamp) request.getAttribute("work_date");
//	        Timestamp workDateTotal = (Timestamp) request.getAttribute("work_date_total");
//
//	        System.out.println("work_date: " + workDate);
//	        System.out.println("work_date_total: " + workDateTotal);
		int token = 1;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<ReportVO> reportListJsp = reportService.reportListTotalJava(searchArea, tableName);
		mav.addObject("reportListJsp", reportListJsp);
		return mav;
	}
	

	

	

	

	

	
	// 일일 보고서 삭제
	@Override
	@GetMapping("/report/removeDailyReport.do")
	public ModelAndView removeDailyReport(@RequestParam("board_date") String work_date, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
		
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int result = 0;
		result = reportService.removeDailyReport(searchArea, work_date);
		
		return mav;
	}
	
	
	// 월별 보고서 게시판 접속
	@Override
	@GetMapping("/report/reportAreaTotal.do")
	public ModelAndView reportAreaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportAreaTotal");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> reportListTotalJsp = new ArrayList<ReportVO>();
		// 월별 작업현황 토큰 1
		int token = 2;
		String tableName = BoardType.fromToken(token).getTableName();
		reportListTotalJsp = reportService.reportListTotalJava(searchArea, tableName);
		mav.addObject("reportListTotalJsp", reportListTotalJsp);
		return mav;
	}
	

	

	

	

	

	

	// sow 일별 게시판 접속
	@Override
	@GetMapping("/report/sowBoard.do")
	public ModelAndView sowBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowBoard");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int token = 3;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<ReportVO> sowBoardList = reportService.reportListTotalJava(searchArea, tableName);
		mav.addObject("sowBoardList", sowBoardList);
		return mav;
	}
	
	// sow 일별 추가 폼
	@Override
	@GetMapping("/report/sowAddForm.do")
	public ModelAndView sowAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowAddForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		SimpleDateFormat date_now = new SimpleDateFormat("yyyy-MM-dd");
        String work_date = date_now.format(new Date());
		
		String searchDate = work_date.substring(0,7);
		
		List<ReportVO> sowWorkName = new ArrayList<ReportVO>();
		sowWorkName = reportDAO.selectWorkName(searchArea);
		
		List<ReportVO> sowMonthList = new ArrayList<ReportVO>();
		sowMonthList = reportService.selectSowMonthList(searchArea, searchDate);
		
		mav.addObject("sowWorkName", sowWorkName); 
		mav.addObject("sowMonthList", sowMonthList);
		mav.addObject("searchArea", searchArea);
		mav.addObject("work_date", work_date);
		return mav;
	}
	
	// sow 일별 추가 (정보저장)
	@Override
	@PostMapping("/report/sowAddDailyWorkLog.do")
	public ModelAndView sowAddDailyWorkLog(@RequestParam(value = "sowDWL_name", required=false) String[] sowDWL_nameArray,
			@RequestParam(value = "sowDWL_work_name", required=false) String[] sowDWL_work_nameArray,
			@RequestParam(value = "sowDWL_shift", required=false) String[] sowDWL_shiftArray,
			@RequestParam(value = "sowDWL_hours", required=false) String[] sowDWL_hoursArray,
			@RequestParam(value = "sowDWL_overtime", required=false) String[] sowDWL_overtimeArray,
			@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowBoard");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<ReportVO> sowDailyWorkLogList = new ArrayList<>();
		for (int i = 0; i < sowDWL_nameArray.length; i++) {
			ReportVO reportVO = new ReportVO();
			reportVO.setSowDWL_name(sowDWL_nameArray[i]);
			reportVO.setSowDWL_work_name(sowDWL_work_nameArray[i]);
			reportVO.setSowDWL_shift(sowDWL_shiftArray[i]);
			reportVO.setSowDWL_hours(nullReturnZero(sowDWL_hoursArray[i]));
			reportVO.setSowDWL_overtime(nullReturnZero(sowDWL_overtimeArray[i]));
			sowDailyWorkLogList.add(reportVO);
		}
		reportService.sowAddDailyWorkLogList(searchArea, sowDailyWorkLogList, work_date);
		
		return mav;
	}
	
	private int nullReturnZero(String Array) {
		try {
			return (Array != null && !Array.trim().isEmpty()) ? Integer.parseInt(Array.trim()) : 0;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// sow 일별 보기
	@Override
	@GetMapping("/report/sowDailyView.do")
	public ModelAndView sowView(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowDailyView");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		CombinedSowDailyWorkLog data = reportService.getCombinedSowDailyWorkLog(searchArea, board_date);
		
		
		
		List<ReportVO> sowViewList = data.getSowDailyWorkLog();
		List<ReportVO> sumOverTime = data.getSumOverTime();
//		sowViewList = reportService.selectViewList(searchArea, board_date);
		List<ReportVO> workNameList = new ArrayList<ReportVO>();
		
		String searchDate = board_date.substring(0,7);
		
		List<ReportVO> sowWorkName = new ArrayList<ReportVO>();
		sowWorkName = reportDAO.selectWorkName(searchArea);
		mav.addObject("sowWorkName", sowWorkName);
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		mav.addObject("work_date", board_date);
		return mav;
	}
	
	// sow 월별 게시판 접속
	@Override
	@GetMapping("/report/sowBoardTotal.do")
	public ModelAndView sowBoardTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowBoardTotal");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 월별 근무현황 토큰 2
//		int token = 2;
//		List<ReportVO> sowListTotalJsp = new ArrayList<ReportVO>();
//		sowListTotalJsp = reportService.reportListTotalJava(searchArea, token);
		
		int token = 4;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<ReportVO> sowListTotalJsp = reportService.reportListTotalJava(searchArea, tableName);
		mav.addObject("sowListTotalJsp", sowListTotalJsp);
		return mav;
	}
	

	
	// sow 월별 추가(정보저장)
	@Override
	@PostMapping("/report/sowAddTotal.do")
	public ModelAndView sowAddTotal(@RequestParam("sowMWL_name") String sowMWL_name, @RequestParam("searchDate") String searchDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/sowAddTotalForm.do?board_date="+searchDate+"-01");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
	
		String work_date = searchDate + "-01";
		
		int insert = 0;
		insert = reportService.sowAddTotal(searchArea, sowMWL_name, work_date);
		return mav;
	}
	
	@GetMapping("/report/testList.do")
	public ModelAndView testList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/testList");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
        
		String board_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		CombinedSowDailyWorkLog data = reportService.getCombinedSowDailyWorkLog(searchArea, board_date);
		
		List<ReportVO> sowViewList = data.getSowDailyWorkLog();
		List<ReportVO> sumOverTime = data.getSumOverTime();

//		sowViewList = reportService.selectViewList(searchArea, board_date);
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		mav.addObject("work_date", board_date);
		return mav;
	}
	
	@GetMapping("/report/testList3.do")
	public ModelAndView testList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/testList3");

		String searchArea="울산";
		String board_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		CombinedSowDailyWorkLog data = reportService.getCombinedSowDailyWorkLog(searchArea, board_date);
		
		List testList2 = new ArrayList<>();
		testList2 = reportDAO.selectTestList2();
		List ulsan = new ArrayList<>();
		String sUlsan = "울산";
		ulsan = reportDAO.selectTestList3(sUlsan);
		List yeosu = new ArrayList<>();
		String sYeosu = "여수";
		yeosu = reportDAO.selectTestList3(sYeosu);
		mav.addObject("testList2", testList2);
		mav.addObject("ulsan", ulsan);
		mav.addObject("yeosu", yeosu);
		return mav;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	// 2025-04-17 전체 수정 후
	
	// 작업현황 현장 추가 폼
	@Override
	@GetMapping("/report/addTotalReportForm.do")
	public ModelAndView addTotalReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/addTotalReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<ReportVO> addReport_total = reportService.selectWorkTotal(searchArea);
		
		mav.addObject("addReport_total", addReport_total);
	
		return mav;
	}
	
	// 작업현황 현장 추가
	@Override
	@PostMapping("/report/addTotalReport.do")
	public ModelAndView addTotalReport(@ModelAttribute("addTotalReport") ReportVO addTotalReport, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/addTotalReportForm.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		reportService.addTotalReport(addTotalReport, searchArea);
		return mav;
	}
	
	// 작업현황 현장 수정 폼
	@Override
	@GetMapping("/report/modTotalReportForm.do")
	public ModelAndView modTotalReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/modTotalReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 작업 현황 가져오는 리스트
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.selectWorkTotal(searchArea);
		mav.addObject("addReport_total", addReport_total);
		
		return mav;
	}
	
	// 작업현황 현장 수정(정보저장)
	@Override
	@PostMapping("/report/modTotalReport.do")
	public ModelAndView modTotalReport(@RequestParam(value = "work_num_total", required = false) String[] work_num_totalArray,
			@RequestParam(value = "work_name_total", required = false) String[] work_name_totalArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/addTotalReportForm.do");
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			
			
			List<ReportVO> modTotalReportList = new ArrayList<>();
			for (int i = 0; i < work_name_totalArray.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_num_total(Integer.parseInt(work_num_totalArray[i]));
				reportVO.setWork_name_total(work_name_totalArray[i]);
				reportVO.setWork_xray_total(work_xray_totalArray[i]);
				reportVO.setWork_PAUT_total(work_PAUT_totalArray[i]);
				reportVO.setWork_charyang_total(work_charyang_totalArray[i]);
				modTotalReportList.add(reportVO);
			}
			
			reportService.modTotalReportList(searchArea, modTotalReportList);
			
			return mav;
		}
	
	// 작업현황 행 삭제	
	@PostMapping("/report/removeTotalReportRow.do")
	@ResponseBody
	public ReportVO removeTotalReportRow(@RequestParam("work_num_total") int work_num_total,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int result = reportService.removeTotalReportRow(work_num_total);
		ReportVO removeSum = new ReportVO();
//		Gson gson = new Gson();
//		JsonArray jArray = new JsonArray();
//		Iterator<ReportVO> it = removeSum.iterator();
//		while(it.hasNext()) {
//			ReportVO reportvo = it.next();
//			JsonObject object = new JsonObject();
//		}
		return removeSum;
	}
	
	// 일일 보고서 글쓰기 양식
	@Override
	@GetMapping("/report/addDailyReportForm.do")
	public ModelAndView addDailyReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/addDailyReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 현재 날짜 기준 연월 받아오기
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
//		Date now = new Date();
//		String date_now = sdf1.format(now);
//		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//		YearMonth yearMonth = YearMonth.parse(date_now, formatter);
//        
//		String work_date_total = yearMonth.toString();
		
//		SimpleDateFormat date_now = new SimpleDateFormat("yyyy-MM-dd");
//        String work_date = date_now.format(new Date());
//		
//		String searchDate = work_date.substring(0,7);
		
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.selectWorkTotal(searchArea);
		mav.addObject("addReport_total", addReport_total);
		return mav;
	}
	
	// 일일 보고서 글쓰기(정보저장)
	@Override
	@PostMapping("/report/addDailyReport.do")
	public ModelAndView addReport(@RequestParam(value = "work_name", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTArray,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTArray,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDArray,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTArray,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTArray,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			@RequestParam(value = "work_num_total", required = false) int[] work_num_totalArray,
			@RequestParam("work_date") String work_date, @RequestParam("board_title") String board_title,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();

			List<ReportVO> workReportList = new ArrayList<>();
			for (int i = 0; i < work_amount_RTArray.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_name(work_nameArray[i]);
				reportVO.setWork_amount_RT(safeParseInt(work_amount_RTArray[i]));
				reportVO.setWork_amount_PAUT(safeParseInt(work_amount_PAUTArray[i]));
				reportVO.setWork_amount_TOFD(safeParseInt(work_amount_TOFDArray[i]));
				reportVO.setWork_amount_UT(safeParseInt(work_amount_UTArray[i]));
				reportVO.setWork_amount_MPT(safeParseInt(work_amount_MPTArray[i]));
				reportVO.setWork_manpower(safeParseInt(work_manpowerArray[i]));
				reportVO.setWork_xray(work_xray_totalArray[i]);
				reportVO.setWork_PAUT(work_PAUT_totalArray[i]);
				reportVO.setWork_charyang(work_charyang_totalArray[i]);
				reportVO.setWork_date(work_date);
				reportVO.setWork_num_total(work_num_totalArray[i]);
				workReportList.add(reportVO);
			}
			reportService.addWorkReportList(searchArea, workReportList, board_title);
			return mav;
		}
		
		private int safeParseInt(String str) {
			if (str == null || str.trim().equals("")) {
				return 0;
			}
			return Integer.parseInt(str);
		}
		
	// 일일 보고서 보기
	@Override
	@GetMapping("/report/reportView.do")
	public ModelAndView reportView(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getDailyReportView(board_date, "report/reportView", request);
	}
	
	// 일일 보고서 수정 폼
	@Override
	@GetMapping("/report/modDailyReportForm.do")
	public ModelAndView modDailyReport(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getDailyReportView(board_date, "report/modDailyReportForm", request);
	}
	
	// 일일 보고서 보기 및 수정 폼
	public ModelAndView getDailyReportView(String board_date, String viewName, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(viewName);
	    HttpSession session = request.getSession();
	    LoginVO login = (LoginVO) session.getAttribute("login");
	    String searchArea = login.getLogin_area();

	    DailyReportWorkrate dailyReport = reportService.dailyReportWorkrate(searchArea, board_date);
	    List<ReportVO> dailyReportViewMerged = dailyReport.getDailyReportViewMerged();
	    ReportVO totalSum = dailyReport.getTotalSum();
	    ReportVO dailySum = dailyReport.getDailySum();

	    mav.addObject("dailyReportViewMerged", dailyReportViewMerged);
	    mav.addObject("totalSum", totalSum);
	    mav.addObject("dailySum", dailySum);
	    mav.addObject("board_date", board_date);
	    
		// 수정사항 체크
		List<ReportVO> getModLog = new ArrayList<ReportVO>();
		int getCountModLog = reportDAO.getCountModLog(searchArea, board_date);
		List<ReportVO> getModDate = reportDAO.getModDate(searchArea, board_date);
		mav.addObject("getCountModLog", getCountModLog);
		if(getCountModLog != 0) {
//			getModLog = reportService.selectModLog(searchArea, board_date);
//			mav.addObject("getModLog", getModLog);
			// 수정사항 존재 시 수정날짜 / 수정자 반환
			mav.addObject("getModDate", getModDate);
			return mav;
		}
		
	    return mav;
	}
	
	// 일일 보고서 수정(정보저장)
	@Override
	@PostMapping("/report/modDailyReport.do")
	public ModelAndView modReport(@RequestParam(value = "work_name", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTArray,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTArray,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDArray,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTArray,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTArray,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam("board_date") String board_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/reportView.do?board_date="+board_date);
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			String login_id = login.getLogin_id();
			System.out.println(board_date);
			List<ReportVO> modReportList = new ArrayList<>();
			for (int i = 0; i < work_amount_RTArray.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_name(work_nameArray[i]);
				reportVO.setWork_amount_RT(Integer.parseInt(work_amount_RTArray[i]));
				reportVO.setWork_amount_PAUT(Integer.parseInt(work_amount_PAUTArray[i]));
				reportVO.setWork_amount_TOFD(Integer.parseInt(work_amount_TOFDArray[i]));
				reportVO.setWork_amount_UT(Integer.parseInt(work_amount_UTArray[i]));
				reportVO.setWork_amount_MPT(Integer.parseInt(work_amount_MPTArray[i]));
				reportVO.setWork_manpower(Integer.parseInt(work_manpowerArray[i]));
				modReportList.add(reportVO);
			}
			reportService.modWorkReportList(searchArea, modReportList, board_date, login_id);
			
			return mav;
		}
	
	// -------------------------------------------------------------------------------------------------------------------
	
	// sow 기본설정 폼
	@Override
	@GetMapping("/report/sowAddTotalForm.do")
	public ModelAndView sowAddTotalForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowAddTotalForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();

		return mav;
	}
}


// 일별 보고서 보기
//@Override
//@GetMapping("/report/reportView.do")
//public ModelAndView reportView(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception
//{
//	ModelAndView mav = new ModelAndView("/report/reportView");
//	HttpSession session = request.getSession();
//	LoginVO login = (LoginVO) session.getAttribute("login");
//	String searchArea = login.getLogin_area();
//
//    // `yyyy-MM-dd` 형식으로 변환
////    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
////    LocalDate localDate = LocalDate.parse(work_dateStr, formatter);
////
////    // LocalDate -> java.util.Date 변환
////    Date date = java.sql.Date.valueOf(localDate);
//
////	List<ReportVO> dailyReport = new ArrayList<ReportVO>();
////	dailyReport = reportService.dailyReportInfo(searchArea, board_date);
//	
//	String searchDate = board_date.substring(0,7);
//	
//	List<ReportVO> addReport_total = new ArrayList<ReportVO>();
//	addReport_total = reportService.selectWorkTotal(searchArea);
//	
//	CombinedReportResponse data = reportService.getCombinedReport(searchArea);
//
//	List<ReportVO> dailyReport = data.getReportList();
//	ReportVO addReport_sum = data.getSingleReport();
//		
//	List<ReportVO> mergedList = new ArrayList<>();
//	//--------------------------------------------------------------------------------
//	for (ReportVO daily : dailyReport) {
//	    for (ReportVO total : addReport_total) {
//	        if (daily.getWork_name().equals(total.getWork_name_total())) {
//
//	            ReportVO merged = new ReportVO();
//
//	            // 이름 통합
//	            merged.setWork_name(daily.getWork_name());
//
//	            // 각각의 데이터를 매칭하여 set
//	            merged.setWork_amount_RT(daily.getWork_amount_RT());
//	            merged.setWork_amount_RT_total(total.getWork_amount_RT_total());
//
//	            merged.setWork_amount_PAUT(daily.getWork_amount_PAUT());
//	            merged.setWork_amount_PAUT_total(total.getWork_amount_PAUT_total());
//
//	            merged.setWork_amount_TOFD(daily.getWork_amount_TOFD());
//	            merged.setWork_amount_TOFD_total(total.getWork_amount_TOFD_total());
//
//	            merged.setWork_amount_UT(daily.getWork_amount_UT());
//	            merged.setWork_amount_UT_total(total.getWork_amount_UT_total());
//
//	            merged.setWork_amount_MPT(daily.getWork_amount_MPT());
//	            merged.setWork_amount_MPT_total(total.getWork_amount_MPT_total());
//
//	            merged.setWork_manpower(daily.getWork_manpower());
//	            merged.setWork_manpower_total(total.getWork_manpower_total());
//
//	            merged.setWork_xray_total(total.getWork_xray_total());
//	            merged.setWork_PAUT_total(total.getWork_PAUT_total());
//	            merged.setWork_charyang_total(total.getWork_charyang_total());
//
//	            mergedList.add(merged);
//	            break; // 중복 방지용
//	        }
//	    }
//	}
//	
//	mav.addObject("work_date", board_date);
//	mav.addObject("mergedList", mergedList);
//	mav.addObject("addReport_sum", addReport_sum);
//	return mav;
//}

