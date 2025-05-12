package com.sboot.pro.argus.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.dao.SowDAO;
import com.sboot.pro.argus.service.CommonService;
import com.sboot.pro.argus.service.SowService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ReportVO;
import com.sboot.pro.argus.vo.SowVO;
import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller("sowController")
public class SowControllerImpl implements SowController {
	
	@Autowired
	private SowService sowService;
	
	@Autowired
	private SowDAO sowDAO;
	
	@Autowired
	private SowVO SowVO;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private WorkingDailyBaseVO workingDailyBaseVO;
	
	// sow 일일 게시판 접속
	@Override
	@GetMapping("/report/sowBoard.do")
	public ModelAndView sowBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowBoard");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int token = 3;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<WorkingDailyBaseVO> sowBoardList = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("sowBoardList", sowBoardList);
		return mav;
	}
	
	// sow 일일 추가 폼
	@Override
	@GetMapping("/report/sowAddForm.do")
	public ModelAndView sowAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowAddForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
				
		List<SowVO> fmonthName = new ArrayList<SowVO>();
		fmonthName = sowDAO.selectWorkName(searchArea);
		
		List<SowVO> empList = new ArrayList<SowVO>();
		empList = sowService.selectEmployeeList(searchArea);
		
		mav.addObject("fmonthName", fmonthName); 
		mav.addObject("empList", empList);
		mav.addObject("searchArea", searchArea);
		return mav;
	}
	
	// sow 일일 추가 (정보저장)
	@Override
	@PostMapping("/report/sowAddDailyWorkLog.do")
	public ModelAndView sowAddDailyWorkLog(@RequestParam(value = "sowDWL_name", required=false) String[] sowDWL_nameArray,
			@RequestParam(value = "sowDWL_work_name", required=false) String[] sowDWL_work_nameArray,
			@RequestParam(value = "sowDWL_shift", required=false) String[] sowDWL_shiftArray,
			@RequestParam(value = "sowDWL_hours", required=false) String[] sowDWL_hoursArray,
			@RequestParam(value = "sowDWL_overtime", required=false) String[] sowDWL_overtimeArray,
			@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/sowBoard.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		int forCounting = sowDAO.countNameLength(searchArea);
		List<SowVO> sowDailyWorkLogList = new ArrayList<>();
		for (int i = 0; i < forCounting-1; i++) {
			SowVO SowVO = new SowVO();
			SowVO.setSowDWL_name(sowDWL_nameArray[i]);
			SowVO.setSowDWL_work_name(sowDWL_work_nameArray[i]);
			SowVO.setSowDWL_shift(sowDWL_shiftArray[i]);
			SowVO.setSowDWL_hours(nullReturnZero(sowDWL_hoursArray[i]));
			SowVO.setSowDWL_overtime(nullReturnZero(sowDWL_overtimeArray[i]));
			sowDailyWorkLogList.add(SowVO);
		}
		System.out.println(work_date);
		sowService.sowAddDailyWorkLogList(searchArea, sowDailyWorkLogList, work_date);
		
		return mav;
	}
	
	private int nullReturnZero(String Array) {
		try {
			return (Array != null && !Array.trim().isEmpty()) ? Integer.parseInt(Array.trim()) : 0;
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// sow 일일 보기
	@Override
	@GetMapping("/report/sowDailyView.do")
	public ModelAndView sowView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowDailyView");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 일일보기 및 추가누계 불러오기
		CombinedSowDailyWorkLog data = sowService.getCombinedSowDailyWorkLog(searchArea, work_date);
		
		List<SowVO> sowViewList = data.getSowDailyWorkLog();
		List<SowVO> sumOverTime = data.getSumOverTime();
		
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		
		// 작업명 불러오기
		List<SowVO> sowWorkName = new ArrayList<SowVO>();
		sowWorkName = sowDAO.selectWorkName(searchArea);
		mav.addObject("sowWorkName", sowWorkName);

		// 주간추가 - 야간 - 야간추가 검색
		List<SowVO> dayNightOvertime = new ArrayList<SowVO>();
		dayNightOvertime = sowService.selectDayNightOvertime(searchArea, work_date);
		mav.addObject("dayNightOvertime", dayNightOvertime);
		
		// 기타 근무별 데이터 조회
		List<SowVO> shiftType = new ArrayList<SowVO>();
		shiftType = sowService.selectShiftType(searchArea, work_date);
		
		List<String> shiftNames = List.of("교육", "출장(입)", "출장(출)", "경조", "시험", "연차", "병가", "훈련", "기타", "비고");
		mav.addObject("shiftNames", shiftNames);

		mav.addObject("shiftType", shiftType);
		// 날짜
		mav.addObject("work_date", work_date);
		
		return mav;
	}
	
	// sow 일일 수정 폼
	@Override
	@GetMapping("/report/sowModDailyForm.do")
	public ModelAndView sowModDailyForm(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowModDailyForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		CombinedSowDailyWorkLog data = sowService.getCombinedSowDailyWorkLog(searchArea, work_date);

		List<SowVO> sowViewList = data.getSowDailyWorkLog();
		List<SowVO> sumOverTime = data.getSumOverTime();
//			sowViewList = sowService.selectViewList(searchArea, work_date);
		List<SowVO> workNameList = new ArrayList<SowVO>();
		
		String searchDate = work_date.substring(0,7);
		
		List<SowVO> sowWorkName = new ArrayList<SowVO>();
		sowWorkName = sowDAO.selectWorkName(searchArea);
		
		mav.addObject("sowWorkName", sowWorkName);
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		mav.addObject("work_date", work_date);
		
		/////////////////
		
		List<SowVO> fmonthName = new ArrayList<SowVO>();
		fmonthName = sowDAO.selectWorkName(searchArea);
		
		List<SowVO> empList = new ArrayList<SowVO>();
		empList = sowService.selectEmployeeList(searchArea);
		
		mav.addObject("fmonthName", fmonthName); 
		mav.addObject("empList", empList);
		mav.addObject("searchArea", searchArea);
		return mav;
	}
	
	// 게시판 접속 공용
	@Override
	@GetMapping("/report/sowBoardTotal.do")
	public ModelAndView sowBoardTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowBoardTotal");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 월별 근무현황 토큰 2
//			int token = 2;
//			List<SowVO> sowListTotalJsp = new ArrayList<SowVO>();
//			sowListTotalJsp = sowService.reportListTotalJava(searchArea, token);
		
		int token = 4;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<WorkingDailyBaseVO> sowListTotalJsp = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("sowListTotalJsp", sowListTotalJsp);
		return mav;
	}
	
	// 직원 목록
	@Override
	@GetMapping("/report/sowAddEmployeeForm.do")
	public ModelAndView sowAddEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/sowAddEmployeeForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<SowVO> employeeList = sowService.selectEmployeeList(searchArea);
		mav.addObject("employeeList", employeeList);
		return mav;
	}
	
	// 직원 등록 (정보저장)
	@Override
	@PostMapping("/report/sowAddEmployee.do")
	public ModelAndView sowAddEmployee(@RequestParam("emp_name") String emp_name, @RequestParam("emp_position") String emp_position, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/sowAddEmployeeForm.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		sowService.sowAddEmployee(searchArea, emp_name, emp_position);
		return mav;
	}
	
	// 직원 수정 및 삭제 ajax
	
	@PostMapping("/report/updateEmployee.do")
	@ResponseBody
	public String updateEmployee(SowVO employee) throws Exception {
		int result = sowDAO.updateEmployee(employee);
		return "ok";
	}
	
	@PostMapping("/report/deleteEmployee.do")
	@ResponseBody
	public String deleteEmployee(SowVO dummyInt) throws Exception {
		sowDAO.deleteEmployee(dummyInt);
		return "ok";
	}
	
	// 일일 보기
	@Override
	public CombinedSowDailyWorkLog getCombinedSowDailyWorkLog(String searchArea, String work_date) throws Exception {
		List<SowVO> selectViewList = new ArrayList<>();
		selectViewList = sowDAO.selectViewList(searchArea, work_date);
		String searchDate = work_date.substring(0,7) + "-01";
		List<SowVO> sumOverTime = new ArrayList<>();
		sumOverTime = sowDAO.selectSumOverTime(searchArea, work_date, searchDate);
		CombinedSowDailyWorkLog response = new CombinedSowDailyWorkLog();
		response.setSowDailyWorkLog(selectViewList);
		response.setSumOverTime(sumOverTime);
		
		return response;
	}
	

}
