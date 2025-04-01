package com.sboot.pro.argus.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
	
//	@Autowired
//	private ReportVO reportVO;
	
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
			
//			List<ReportVO> workReportList = new ArrayList<>();
//			for (int i = 0; i < work_amount_RTStr.length; i++) {
//			    workReportList.add(new ReportVO(
//			        Integer.parseInt(work_amount_RTStr[i]),
//			        Integer.parseInt(work_amount_PAUTStr[i]),
//			        Integer.parseInt(work_amount_TOFDStr[i]),
//			        Integer.parseInt(work_amount_UTStr[i]),
//			        Integer.parseInt(work_amount_MPTStr[i]),
//			        Integer.parseInt(work_manpowerArray[i]),
//			        work_xrayArray[i],
//			        work_PAUTArray[i],
//			        work_charyangArray[i]
//			    ));
//			}
			mav.setViewName("report/testList");
//			JsonArray jArray = new JsonArray();
//			Iterator<ReportVO> it = workReportList.iterator();
//			while(it.hasNext()) {
//				ReportVO test = it.next();
//				JsonObject object = new JsonObject();
//				int RT = test.getWork_amount_RT();
//				int PAUT = test.getWork_amount_PAUT();
//				int TOFD = test.getWork_amount_TOFD();
//				int UT = test.getWork_amount_UT();
//				int MPT = test.getWork_amount_MPT();
//				int manpower = test.getWork_manpower();
//				String xray = test.getWork_xray();
//				String PAUT2 = test.getWork_PAUT();
//				String charyang = test.getWork_charyang();
//				System.out.println(object);
//			}
			return mav;
			
//			List beforeBmCal = adminService.beforeBmCal();
//			Gson gson = new Gson();
//			JsonArray jArray = new JsonArray();
//			Iterator<AdminVO> it = beforeBmCal.iterator();
//			while(it.hasNext()) {
//				AdminVO admin = it.next();
//				JsonObject object = new JsonObject();
//				int beforeSetPrice = admin.getSetAcc_price();
//				String beforeStore_category = admin.getStore_category();
//				
//				object.addProperty("beforeSetPrice", beforeSetPrice);
//				object.addProperty("beforeStore_category", beforeStore_category);
//				jArray.add(object);
//			}
//			
//			String json = gson.toJson(jArray);
//			System.out.println("json변환:"+json);
//			return json;
		}
	}


//reportService.addWorkReportList(workReportList);