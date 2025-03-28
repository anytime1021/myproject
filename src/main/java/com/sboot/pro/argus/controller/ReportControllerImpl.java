package com.sboot.pro.argus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
		return mav;
	}
	
	// 보고서 글쓰기 양식
	@Override
	@GetMapping("/report/addReportForm.do")
	public ModelAndView addReportForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/addReportForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.addReportForm(searchArea);
		mav.addObject("addReport_total", addReport_total);
		return mav;
	}
	
	@Override
	@PostMapping("/report/addReport.do")
	public ModelAndView addReport(@RequestParam(value = "work_amount_RT", required = false) String[] work_amount_RTStr,
			@RequestParam(value = "work_amount_PAUT", required = false) String[] work_amount_PAUTStr,
			@RequestParam(value = "work_amount_TOFD", required = false) String[] work_amount_TOFDStr,
			@RequestParam(value = "work_amount_UT", required = false) String[] work_amount_UTStr,
			@RequestParam(value = "work_amount_MPT", required = false) String[] work_amount_MPTStr,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam(value = "work_xray", required = false) String[] work_xrayArray,
			@RequestParam(value = "work_PAUT", required = false) String[] work_PAUTArray,
			@RequestParam(value = "work_charyang", required = false) String[] work_charyangArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("/argus/main");
			
			List<ReportVO> workReportList = new ArrayList<>();
	        for (int i = 0; i < work_amount_RTStr.length; i++) {
	            workReportList.add(new ReportVO(
	                Integer.parseInt(work_amount_RTStr[i]),
	                Integer.parseInt(work_amount_PAUTStr[i]),
	                Integer.parseInt(work_amount_TOFDStr[i]),
	                Integer.parseInt(work_amount_UTStr[i]),
	                Integer.parseInt(work_amount_MPTStr[i]),
	                work_manpowerArray[i],
	                work_xrayArray[i],
	                work_PAUTArray[i],
	                work_charyangArray[i]
	            ));
	        }
			
	        reportService.addWorkReportList(workReportList);
	        
			return mav;
		}
	}
