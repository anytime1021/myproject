package com.sboot.pro.argus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.DTO.CombinedReportResponse;
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
	
	// 일별 보고서 보기
	@Override
	@GetMapping("/report/reportView.do")
	public ModelAndView reportView(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView("/report/reportView");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();

        // `yyyy-MM-dd` 형식으로 변환
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(work_dateStr, formatter);
//
//        // LocalDate -> java.util.Date 변환
//        Date date = java.sql.Date.valueOf(localDate);

//		List<ReportVO> dailyReport = new ArrayList<ReportVO>();
//		dailyReport = reportService.dailyReportInfo(searchArea, board_date);
		
		String searchDate = board_date.substring(0,7);
		
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea, searchDate);
		
		CombinedReportResponse data = reportService.getCombinedReport(searchArea, board_date);

		List<ReportVO> dailyReport = data.getReportList();
		ReportVO addReport_sum = data.getSingleReport();
		
		ReportVO addReport_total_sum = reportDAO.selectAddReportSumForm(searchArea, searchDate);
		
		List<ReportVO> mergedList = new ArrayList<>();
		//--------------------------------------------------------------------------------
		for (ReportVO daily : dailyReport) {
		    for (ReportVO total : addReport_total) {
		        if (daily.getWork_name().equals(total.getWork_name_total())) {

		            ReportVO merged = new ReportVO();

		            // 이름 통합
		            merged.setWork_name(daily.getWork_name());

		            // 각각의 데이터를 매칭하여 set
		            merged.setWork_amount_RT(daily.getWork_amount_RT());
		            merged.setWork_amount_RT_total(total.getWork_amount_RT_total());

		            merged.setWork_amount_PAUT(daily.getWork_amount_PAUT());
		            merged.setWork_amount_PAUT_total(total.getWork_amount_PAUT_total());

		            merged.setWork_amount_TOFD(daily.getWork_amount_TOFD());
		            merged.setWork_amount_TOFD_total(total.getWork_amount_TOFD_total());

		            merged.setWork_amount_UT(daily.getWork_amount_UT());
		            merged.setWork_amount_UT_total(total.getWork_amount_UT_total());

		            merged.setWork_amount_MPT(daily.getWork_amount_MPT());
		            merged.setWork_amount_MPT_total(total.getWork_amount_MPT_total());

		            merged.setWork_manpower(daily.getWork_manpower());
		            merged.setWork_manpower_total(total.getWork_manpower_total());

		            merged.setWork_xray_total(total.getWork_xray_total());
		            merged.setWork_PAUT_total(total.getWork_PAUT_total());
		            merged.setWork_charyang_total(total.getWork_charyang_total());

		            mergedList.add(merged);
		            break; // 중복 방지용
		        }
		    }
		}
		
		mav.addObject("work_date", board_date);
		mav.addObject("mergedList", mergedList);
		mav.addObject("addReport_sum", addReport_sum);
		mav.addObject("addReport_total_sum", addReport_total_sum);
		//--------------------------------------------------------------------------------
//		mav.addObject("board_date", board_date);
//		mav.addObject("addReport_total", addReport_total);
//		mav.addObject("dailyReport", dailyReport);
		return mav;
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
		
		SimpleDateFormat date_now = new SimpleDateFormat("yyyy-MM-dd");
        String work_date = date_now.format(new Date());
		
		String searchDate = work_date.substring(0,7);
		
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea, searchDate);
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
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			
			SimpleDateFormat date_now = new SimpleDateFormat("yyyy-MM-dd");
	        String work_date = date_now.format(new Date());

			List<ReportVO> workReportList = new ArrayList<>();
			for (int i = 0; i < work_amount_RTArray.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_name(work_nameArray[i]);
				reportVO.setWork_amount_RT(Integer.parseInt(work_amount_RTArray[i]));
				reportVO.setWork_amount_PAUT(Integer.parseInt(work_amount_PAUTArray[i]));
				reportVO.setWork_amount_TOFD(Integer.parseInt(work_amount_TOFDArray[i]));
				reportVO.setWork_amount_UT(Integer.parseInt(work_amount_UTArray[i]));
				reportVO.setWork_amount_MPT(Integer.parseInt(work_amount_MPTArray[i]));
				reportVO.setWork_manpower(Integer.parseInt(work_manpowerArray[i]));
				reportVO.setWork_date(work_date);
				workReportList.add(reportVO);
			}
			reportService.addWorkReportList(searchArea, workReportList, work_date);
			
			List<ReportVO> test = new ArrayList<ReportVO>();
			test = reportService.testForm(searchArea);
			mav.addObject("testList", test);
			return mav;	
		}
	
	// 일일 보고서 수정 폼
	@Override
	@GetMapping("/report/modDailyReportForm.do")
	public ModelAndView modDailyReport(@RequestParam("board_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/modDailyReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();

        // `yyyy-MM-dd` 형식으로 변환
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate localDate = LocalDate.parse(work_dateStr, formatter);
//
//        // LocalDate -> java.util.Date 변환
//        Date date = java.sql.Date.valueOf(localDate);

		List<ReportVO> dailyReport = new ArrayList<ReportVO>();
		dailyReport = reportService.dailyReportInfo(searchArea, work_date);
		
		String searchDate = work_date.substring(0,7);
		
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea, searchDate);
		
		List<ReportVO> mergedList = new ArrayList<>();
		//--------------------------------------------------------------------------------
		for (ReportVO daily : dailyReport) {
		    for (ReportVO total : addReport_total) {
		        if (daily.getWork_name().equals(total.getWork_name_total())) {

		            ReportVO merged = new ReportVO();

		            // 이름 통합
		            merged.setWork_name(daily.getWork_name());

		            // 각각의 데이터를 매칭하여 set
		            merged.setWork_amount_RT(daily.getWork_amount_RT());
		            merged.setWork_amount_RT_total(total.getWork_amount_RT_total());

		            merged.setWork_amount_PAUT(daily.getWork_amount_PAUT());
		            merged.setWork_amount_PAUT_total(total.getWork_amount_PAUT_total());

		            merged.setWork_amount_TOFD(daily.getWork_amount_TOFD());
		            merged.setWork_amount_TOFD_total(total.getWork_amount_TOFD_total());

		            merged.setWork_amount_UT(daily.getWork_amount_UT());
		            merged.setWork_amount_UT_total(total.getWork_amount_UT_total());

		            merged.setWork_amount_MPT(daily.getWork_amount_MPT());
		            merged.setWork_amount_MPT_total(total.getWork_amount_MPT_total());

		            merged.setWork_manpower(daily.getWork_manpower());
		            merged.setWork_manpower_total(total.getWork_manpower_total());

		            merged.setWork_xray_total(total.getWork_xray_total());
		            merged.setWork_PAUT_total(total.getWork_PAUT_total());
		            merged.setWork_charyang_total(total.getWork_charyang_total());

		            mergedList.add(merged);
		            break; // 중복 방지용
		        }
		    }
		}
		mav.addObject("work_date", work_date);
		mav.addObject("mergedList", mergedList);
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
			@RequestParam("work_date") String work_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			
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
			reportService.modWorkReportList(searchArea, modReportList, work_date);
			
			List<ReportVO> test = new ArrayList<ReportVO>();
			test = reportService.testForm(searchArea);
			mav.addObject("testList", test);
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
	
	// 월별 보고서 전체 양식
	@Override
	@GetMapping("/report/addTotalReportForm.do")
	public ModelAndView addTotalReportForm(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/addTotalReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		String searchDate = board_date.substring(0,7);
		
//		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
//		addReport_total = reportService.addReportForm(searchArea, searchDate);
//		ReportVO addReport_total_sum = new ReportVO();
//		reportService.addReportSumForm(searchArea, searchDate);
		
		CombinedReportResponse data = reportService.getCombinedReport(searchArea, searchDate);

		List<ReportVO> addReport_total = data.getReportList();
		ReportVO addReport_total_sum = data.getSingleReport();
		
		mav.addObject("board_date", board_date);
		mav.addObject("addReport_total", addReport_total);
		mav.addObject("addReport_total_sum", addReport_total_sum);
	
		return mav;
	}
	
	// 월별 보고서 현장 추가
	@Override
	@PostMapping("/report/addTotalReport.do")
	public ModelAndView addTotalReport(@ModelAttribute("addTotalReport") ReportVO addTotal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/addTotalReportForm.do?board_date="+addTotal.getWork_date_total());
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		reportService.addTotalReport(addTotal, searchArea);
		return mav;
	}
	
	// 월별 보고서 수정 폼
	@Override
	@GetMapping("/report/modTotalReportForm.do")
	public ModelAndView modTotalReportForm(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/modTotalReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 현재 날짜 기준 연월 받아오기
		String searchDate = work_date.substring(0,7);
		
		CombinedReportResponse data = reportService.getCombinedReport(searchArea, searchDate);

		List<ReportVO> addReport_total = data.getReportList();
		ReportVO addReport_total_sum = data.getSingleReport();
		
		mav.addObject("board_date", work_date);
		mav.addObject("addReport_total", addReport_total);
		mav.addObject("addReport_total_sum", addReport_total_sum);
		
		return mav;
	}
	
	// 월별 보고서 수정
	// 월별 보고서 수정하기
	@Override
	@PostMapping("/report/modTotalReport.do")
	public ModelAndView modTotalReport(@RequestParam(value = "work_name_total", required =false) String[] work_name_totalArray,
			@RequestParam(value = "work_amount_RT_total", required = false) String[] work_amount_RT_totalArray,
			@RequestParam(value = "work_amount_PAUT_total", required = false) String[] work_amount_PAUT_totalArray,
			@RequestParam(value = "work_amount_TOFD_total", required = false) String[] work_amount_TOFD_totalArray,
			@RequestParam(value = "work_amount_UT_total", required = false) String[] work_amount_UT_totalArray,
			@RequestParam(value = "work_amount_MPT_total", required = false) String[] work_amount_MPT_totalArray,
			@RequestParam(value = "work_manpower_total", required = false) String[] work_manpower_totalArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			@RequestParam("work_date") String work_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/addTotalReportForm.do?board_date=" + work_date);
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			
			String searchDate = work_date.substring(0,7);
			
			List<ReportVO> modTotalReportList = new ArrayList<>();
			for (int i = 0; i < work_amount_RT_totalArray.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_name_total(work_name_totalArray[i]);
				reportVO.setWork_amount_RT_total(Integer.parseInt(work_amount_RT_totalArray[i]));
				reportVO.setWork_amount_PAUT_total(Integer.parseInt(work_amount_PAUT_totalArray[i]));
				reportVO.setWork_amount_TOFD_total(Integer.parseInt(work_amount_TOFD_totalArray[i]));
				reportVO.setWork_amount_UT_total(Integer.parseInt(work_amount_UT_totalArray[i]));
				reportVO.setWork_amount_MPT_total(Integer.parseInt(work_amount_MPT_totalArray[i]));
				reportVO.setWork_manpower_total(Integer.parseInt(work_manpower_totalArray[i]));
				reportVO.setWork_xray_total(work_xray_totalArray[i]);
				reportVO.setWork_PAUT_total(work_PAUT_totalArray[i]);
				reportVO.setWork_charyang_total(work_charyang_totalArray[i]);
				modTotalReportList.add(reportVO);
			}
			
			reportService.modTotalReportList(searchArea, modTotalReportList, searchDate);
			
			return mav;
		}
	
	// 월별 보고서 행 삭제	
	@PostMapping("/report/removeTotalReportRow.do")
	@ResponseBody
	public ReportVO removeTotalReportRow(@RequestParam("work_name_total") String work_name_total, @RequestParam("work_date_total") String work_date_total,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		String searchDate = work_date_total.substring(0,7);
		
		int result = reportService.removeTotalReportRow(searchArea, work_name_total, searchDate);
		ReportVO removeSum = reportDAO.selectAddReportSumForm(searchArea, searchDate);
//		Gson gson = new Gson();
//		JsonArray jArray = new JsonArray();
//		Iterator<ReportVO> it = removeSum.iterator();
//		while(it.hasNext()) {
//			ReportVO reportvo = it.next();
//			JsonObject object = new JsonObject();
//		}
		return removeSum;
	}
	
	// -------------------------------------------------------------------------------------------------------------------
	
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
		
		List<ReportVO> sowWorkName = new ArrayList<ReportVO>();
		
		SimpleDateFormat date_now = new SimpleDateFormat("yyyy-MM-dd");
        String work_date = date_now.format(new Date());
		
		String searchDate = work_date.substring(0,7);
		
		sowWorkName = reportDAO.selectWorkName(searchArea, searchDate);
		List<ReportVO> sowMonthList = new ArrayList<ReportVO>();
		sowMonthList = reportService.selectSowMonthList(searchArea, searchDate);
		mav.addObject("sowWorkName", sowWorkName); 
		mav.addObject("searchArea", searchArea);
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
	
	// sow 월별 추가 폼
	@Override
	@GetMapping("/report/sowAddTotalForm.do")
	public ModelAndView sowAddTotalForm(@RequestParam("board_date") String board_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowAddTotalForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		String searchDate = board_date.substring(0,7);
		
		List<ReportVO> sowWorkName = new ArrayList<ReportVO>();
		sowWorkName = reportDAO.selectWorkName(searchArea, searchDate);
		System.out.println(searchArea);
		System.out.println(searchDate);
		mav.addObject("sowWorkName", sowWorkName);
		mav.addObject("searchArea", searchArea);
		mav.addObject("searchDate", searchDate);
		List<ReportVO> sowName= reportService.selectAddTotal(searchArea, searchDate);
//		boolean listCheck = true;
//		mav.addObject("listCheck", listCheck);
		mav.addObject("sowName", sowName);
		return mav;
	}
	
	// sow 월별 추가(정보저장)
	
	@PostMapping("/report/sowAddTotal.do")
	public ModelAndView sowAddTotal(@RequestParam("sowDML_name") String sowDML_name, @RequestParam("searchDate") String searchDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/sowAddTotalForm.do?board_date="+searchDate+"-01");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
	
		String work_date = searchDate + "-01";
		System.out.println(work_date);
		
		int insert = 0;
		insert = reportService.sowAddTotal(searchArea, sowDML_name, work_date);
		return mav;
	}
}


