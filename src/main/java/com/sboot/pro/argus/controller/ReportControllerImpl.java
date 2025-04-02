package com.sboot.pro.argus.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	
	// 보고서 게시판 접속
	@Override
	@GetMapping("/report/reportArea.do")
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportArea");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> reportListJsp = new ArrayList<ReportVO>();
		reportListJsp = reportService.reportListJava(searchArea);
		mav.addObject("reportListJsp", reportListJsp);
		return mav;
	}
	
	// 일별 보고서 보기
	@Override
	@GetMapping("/report/reportView.do")
	public ModelAndView reportView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView("/report/reportView");
		System.out.println(work_date);
        
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> dailyReport = new ArrayList<ReportVO>();
		dailyReport = reportService.dailyReportInfo(searchArea, work_date);
		mav.addObject("dailyReport", dailyReport);
		return mav;
	}
		
	// 월별 보고서 전체 양식
	@Override
	@GetMapping("/report/totalReportForm.do")
	public ModelAndView totalReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/totalReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea);
		mav.addObject("addReport_total", addReport_total);
		return mav;
	}
	
	// 월별 보고서 현장 추가
	@Override
	@PostMapping("/report/addTotalReport.do")
	public ModelAndView addTotalReport(@ModelAttribute("addTotal") ReportVO addTotal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/totalReportForm.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		reportService.addTotalReport(addTotal, searchArea);
		return mav;
	}
	
	// 일일 보고서 글쓰기 양식
	@Override
	@GetMapping("/report/addWorkReportForm.do")
	public ModelAndView addReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/addWorkReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea);
		mav.addObject("addReport_total", addReport_total);
		return mav;
	}
	
	// 보고서 글쓰기(정보저장)
	@Override
	@PostMapping("/report/addWorkReport.do")
	public ModelAndView addReport(@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTArray,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTArray,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDArray,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTArray,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTArray,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam(value = "work_xray", required = false) String[] work_xrayArray,
			@RequestParam(value = "work_PAUT", required = false) String[] work_PAUTArray,
			@RequestParam(value = "work_charyang", required = false) String[] work_charyangArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/testList.do");
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			
			List<ReportVO> workReportList = new ArrayList<>();
			for (int i = 0; i < work_amount_RTArray.length; i++) {
				System.out.println(work_amount_RTArray[i]);
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_amount_RT(Integer.parseInt(work_amount_RTArray[i]));
				reportVO.setWork_amount_PAUT(Integer.parseInt(work_amount_PAUTArray[i]));
				reportVO.setWork_amount_TOFD(Integer.parseInt(work_amount_TOFDArray[i]));
				reportVO.setWork_amount_UT(Integer.parseInt(work_amount_UTArray[i]));
				reportVO.setWork_amount_MPT(Integer.parseInt(work_amount_MPTArray[i]));
				reportVO.setWork_manpower(Integer.parseInt(work_manpowerArray[i]));
				reportVO.setWork_xray(work_xrayArray[i]);
				reportVO.setWork_PAUT(work_PAUTArray[i]);
				reportVO.setWork_charyang(work_charyangArray[i]);
				workReportList.add(reportVO);
			}
			reportService.addWorkReportList(searchArea, workReportList);
			
			List<ReportVO> test = new ArrayList<ReportVO>();
			test = reportService.testForm(searchArea);
			mav.addObject("testList", test);
			mav.setViewName("report/testList");
			return mav;
					
			
		}
	}


