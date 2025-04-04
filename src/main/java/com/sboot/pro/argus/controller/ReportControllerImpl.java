package com.sboot.pro.argus.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
import org.springframework.web.servlet.ModelAndView;

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
		List<ReportVO> reportListJsp = new ArrayList<ReportVO>();
		reportListJsp = reportService.reportListJava(searchArea);
//		 Timestamp workDate = (Timestamp) request.getAttribute("work_date");
//	        Timestamp workDateTotal = (Timestamp) request.getAttribute("work_date_total");
//
//	        System.out.println("work_date: " + workDate);
//	        System.out.println("work_date_total: " + workDateTotal);
		
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

		List<ReportVO> dailyReport = new ArrayList<ReportVO>();
		dailyReport = reportService.dailyReportInfo(searchArea, board_date);
		
		String searchDate = board_date.substring(0,7);
		
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

		            merged.setWork_xray(total.getWork_xray());
		            merged.setWork_PAUT(total.getWork_PAUT());
		            merged.setWork_charyang(total.getWork_charyang());

		            mergedList.add(merged);
		            break; // 중복 방지용
		        }
		    }
		}
		mav.addObject("work_date", board_date);
		mav.addObject("mergedList", mergedList);
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
        System.out.println(work_date);
		
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

		            merged.setWork_xray(total.getWork_xray());
		            merged.setWork_PAUT(total.getWork_PAUT());
		            merged.setWork_charyang(total.getWork_charyang());

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
			@RequestParam("board_date") String work_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();

	        System.out.println("----------");
	        System.out.println(work_date);
	        System.out.println("----------");
			
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
	
	
	// 월별 보고서 게시판 접속
	@Override
	@GetMapping("/report/reportAreaTotal.do")
	public ModelAndView reportAreaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportAreaTotal");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> reportListTotalJsp = new ArrayList<ReportVO>();
		reportListTotalJsp = reportService.reportListTotalJava(searchArea);
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
		
		// 현재 날짜 기준 연월 받아오기
		String searchDate = board_date.substring(0,7);
		
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea, searchDate);
		mav.addObject("board_date", board_date);
		mav.addObject("addReport_total", addReport_total);
		
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
//		YearMonth yearMonth = YearMonth.parse(work_date_total, formatter);
//        
//		String work_date_totalStr = yearMonth.toString();
//		
//		LocalDateTime requestTime = (LocalDateTime) request.getAttribute("requestTime");
//		
//		System.out.println(requestTime);
//		
//		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
//		addReport_total = reportService.addReportForm(searchArea, work_date_totalStr);
//		mav.addObject("addReport_total", addReport_total);
//		mav.addObject("work_date_total_this", work_date_total);
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
		

//	@Override
//	@PostMapping("/report/addDailyReport.do")
//	public ModelAndView addReport(@RequestParam(value = "work_name", required =false) String work_nameArray,
//			@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTArray,
//			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTArray,
//			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDArray,
//			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTArray,
//			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTArray,
//			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
//			@RequestParam(value = "work_xray", required = false) String[] work_xrayArray,
//			@RequestParam(value = "work_PAUT", required = false) String[] work_PAUTArray,
//			@RequestParam(value = "work_charyang", required = false) String[] work_charyangArray,
//			HttpServletRequest request, HttpServletResponse response) throws Exception {
//			ModelAndView mav = new ModelAndView("redirect:/report/testList.do");
//			HttpSession session = request.getSession();
//			LoginVO login = (LoginVO) session.getAttribute("login");
//			String searchArea = login.getLogin_area();
//			
//			List<ReportVO> workReportList = new ArrayList<>();
//			for (int i = 0; i < work_amount_RTArray.length; i++) {
//				System.out.println(work_amount_RTArray[i]);
//				ReportVO reportVO = new ReportVO();
//				reportVO.setWork_amount_RT(Integer.parseInt(work_amount_RTArray[i]));
//				reportVO.setWork_amount_PAUT(Integer.parseInt(work_amount_PAUTArray[i]));
//				reportVO.setWork_amount_TOFD(Integer.parseInt(work_amount_TOFDArray[i]));
//				reportVO.setWork_amount_UT(Integer.parseInt(work_amount_UTArray[i]));
//				reportVO.setWork_amount_MPT(Integer.parseInt(work_amount_MPTArray[i]));
//				reportVO.setWork_manpower(Integer.parseInt(work_manpowerArray[i]));
//				reportVO.setWork_xray(work_xrayArray[i]);
//				reportVO.setWork_PAUT(work_PAUTArray[i]);
//				reportVO.setWork_charyang(work_charyangArray[i]);
//				workReportList.add(reportVO);
//			}
//			reportService.addWorkReportList(searchArea, workReportList);
//			
//			List<ReportVO> test = new ArrayList<ReportVO>();
//			test = reportService.testForm(searchArea);
//			mav.addObject("testList", test);
//			mav.setViewName("report/testList");
//			return mav;	
//		}
	}

