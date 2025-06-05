package com.sboot.pro.argus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.dao.SowDAO;
import com.sboot.pro.argus.service.CommonService;
import com.sboot.pro.argus.service.SowService;
import com.sboot.pro.argus.vo.LoginVO;
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
	@GetMapping("/sow/sowBoard.do")
	public ModelAndView sowBoard(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/sow/sowBoard");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int token = 2;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<WorkingDailyBaseVO> sowBoardList = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("sowBoardList", sowBoardList);
		return mav;
	}
	
	// sow 일일 추가 폼
	@Override
	@GetMapping("/sow/sowAddForm.do")
	public ModelAndView sowAddForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/sow/sowAddForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 현장명
		List<SowVO> fmonthName = new ArrayList<SowVO>();
		fmonthName = sowDAO.selectWorkName(searchArea);
		
		// 직원 목록
		List<SowVO> empList = new ArrayList<SowVO>();
		empList = sowService.selectEmployeeList(searchArea);
		
		// 근무시간
		List<Integer> hoursList = new ArrayList<>();
		for (int i=0; i<empList.size(); i++) {
			hoursList.add(8);
		}
		
		// 출장자 목록
		List<SowVO> btInList = new ArrayList<SowVO>();
		String bt_in = "in";
		btInList = sowService.selectBtEmployeeList(searchArea, bt_in);
		
		String bt_out = "out";
		List<SowVO> btOutList = new ArrayList<SowVO>();
		btOutList = sowService.selectBtEmployeeList(searchArea, bt_out);

		// 출장자 근무시간
		List<Integer> btInHoursList = new ArrayList<>();
		for (int i=0; i<btInList.size(); i++) {
			btInHoursList.add(8);
		}
		List<Integer> btOutHoursList = new ArrayList<>();
		for (int i=0; i<btOutList.size(); i++) {
			btOutHoursList.add(8);
		}
		
		// 출장자 count - 테이블 구조 유지 조건
		int btInCount = sowDAO.countBtList(searchArea, bt_in);
		int btOutCount = sowDAO.countBtList(searchArea, bt_out);
		
		mav.addObject("fmonthName", fmonthName); 
		mav.addObject("empList", empList);
		mav.addObject("searchArea", searchArea);
		mav.addObject("hoursList", hoursList);
		mav.addObject("btInList", btInList);
		mav.addObject("btOutList", btOutList);
		mav.addObject("btInHoursList", btInHoursList);
		mav.addObject("btOutHoursList", btOutHoursList);
		mav.addObject("btInCount", btInCount);
		mav.addObject("btOutCount", btOutCount);
		return mav;
	}
	
	// sow 일일 추가 (정보저장)
	@Override
	@PostMapping("/sow/sowAddDailyWorkLog.do")
	public ModelAndView sowAddDailyWorkLog(@RequestParam(value = "sowDWL_name", required=false) String[] sowDWL_nameArray,
			@RequestParam(value = "sowDWL_work_name", required=false) String[] sowDWL_work_nameArray,
			@RequestParam(value = "sowDWL_shift", required=false) String[] sowDWL_shiftArray,
			@RequestParam(value = "sowDWL_hours", required=false) String[] sowDWL_hoursArray,
			@RequestParam(value = "sowDWL_overtime", required=false) String[] sowDWL_overtimeArray,
			@RequestParam(value = "emp_num", required=false) String[] emp_numArray,
			@RequestParam(value = "sowDWL_name_in", required=false) String[] sowDWL_name_inArray,
			@RequestParam(value = "sowDWL_work_name_in", required=false) String[] sowDWL_work_name_inArray,
			@RequestParam(value = "sowDWL_shift_in", required=false) String[] sowDWL_shift_inArray,
			@RequestParam(value = "sowDWL_hours_in", required=false) String[] sowDWL_hours_inArray,
			@RequestParam(value = "sowDWL_overtime_in", required=false) String[] sowDWL_overtime_inArray,
			@RequestParam(value = "emp_num_in", required=false) String[] emp_num_inArray,
			@RequestParam(value = "sowDWL_name_out", required=false) String[] sowDWL_name_outArray,
			@RequestParam(value = "sowDWL_work_name_out", required=false) String[] sowDWL_work_name_outArray,
			@RequestParam(value = "sowDWL_shift_out", required=false) String[] sowDWL_shift_outArray,
			@RequestParam(value = "sowDWL_hours_out", required=false) String[] sowDWL_hours_outArray,
			@RequestParam(value = "sowDWL_overtime_out", required=false) String[] sowDWL_overtime_outArray,
			@RequestParam(value = "emp_num_out", required=false) String[] emp_num_outArray,
			@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/sow/sowBoard.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		int forCounting = sowDAO.countNameLength(searchArea);
		
		List<SowVO> sowDailyWorkLogList = new ArrayList<>();
		for (int i = 0; i < forCounting-1; i++) {
			SowVO sowvo = new SowVO();
			sowvo.setSowDWL_name(sowDWL_nameArray[i]);
			sowvo.setSowDWL_work_name(sowDWL_work_nameArray[i]);
			sowvo.setSowDWL_shift(sowDWL_shiftArray[i]);
			sowvo.setSowDWL_hours(nullReturnZero(sowDWL_hoursArray[i]));
			sowvo.setSowDWL_overtime(nullReturnZero(sowDWL_overtimeArray[i]));
			sowvo.setEmp_num(nullReturnZero(emp_numArray[i]));
			sowDailyWorkLogList.add(sowvo);
		}
		sowService.sowAddDailyWorkLogList(searchArea, sowDailyWorkLogList, work_date);
		
		int inCounting = sowDAO.countBtNameLength(searchArea, "in");
		int outCounting = sowDAO.countBtNameLength(searchArea, "out");
		
		List<SowVO> sowBusinessTripIn = new ArrayList<>();
		for (int i = 0; i < inCounting-1; i++) {
			SowVO sowvo = new SowVO();
			sowvo.setSowDWL_name(sowDWL_name_inArray[i]);
			sowvo.setSowDWL_work_name(sowDWL_work_name_inArray[i]);
			sowvo.setSowDWL_shift(sowDWL_shift_inArray[i]);
			sowvo.setSowDWL_hours(nullReturnZero(sowDWL_hours_inArray[i]));
			sowvo.setSowDWL_overtime(nullReturnZero(sowDWL_overtime_inArray[i]));
			sowvo.setBt_inout("in");
			sowvo.setEmp_num(nullReturnZero(emp_num_inArray[i]));
			sowBusinessTripIn.add(sowvo);
		}
		if (!sowBusinessTripIn.isEmpty()) {
			sowService.sowAddBusinessTrip(searchArea, sowBusinessTripIn, work_date);
		}
		
		List<SowVO> sowBusinessTripOut = new ArrayList<>();
		for (int i = 0; i < outCounting-1; i++) {
			SowVO sowvo = new SowVO();
			sowvo.setSowDWL_name(sowDWL_name_outArray[i]);
			sowvo.setSowDWL_work_name(sowDWL_work_name_outArray[i]);
			sowvo.setSowDWL_shift(sowDWL_shift_outArray[i]);
			sowvo.setSowDWL_hours(nullReturnZero(sowDWL_hours_outArray[i]));
			sowvo.setSowDWL_overtime(nullReturnZero(sowDWL_overtime_outArray[i]));
			sowvo.setBt_inout("out");
			sowvo.setEmp_num(nullReturnZero(emp_num_outArray[i]));
			sowBusinessTripOut.add(sowvo);
		}
		if (!sowBusinessTripOut.isEmpty()) {
			sowService.sowAddBusinessTrip(searchArea, sowBusinessTripOut, work_date);
		}
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
	@GetMapping("/sow/sowDailyView.do")
	public ModelAndView sowView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/sow/sowDailyView");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 일일보기 및 추가누계 불러오기
		CombinedSowDailyWorkLog data = sowService.getCombinedSowDailyWorkLog(searchArea, work_date);
		
		List<SowVO> sowViewList = data.getSowDailyWorkLog();
		List<SowVO> sumOverTime = data.getSumOverTime();
		
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		
		// 출장자 불러오기
		List<SowVO> btInList = new ArrayList<>();
		btInList = sowService.selectBusinessTrip(searchArea, "in", work_date);
		
		List<SowVO> btOutList = new ArrayList<>();
		btOutList = sowService.selectBusinessTrip(searchArea, "out", work_date);
		
		// 출장자 추가누계
		List<SowVO> btInSumOverTime = new ArrayList<>();
		btInSumOverTime = sowService.btSumOverTime(searchArea, "in", work_date);
		
		List<SowVO> btOutSumOverTime = new ArrayList<>();
		btOutSumOverTime = sowService.btSumOverTime(searchArea, "out", work_date);
		
		String bt_in = "in";
		String bt_out = "out";
		int btInCount = sowService.countBtViewList(searchArea, bt_in, work_date);
		int btOutCount = sowService.countBtViewList(searchArea, bt_out, work_date);
		
		mav.addObject("btInList", btInList);
		mav.addObject("btOutList", btOutList);
		mav.addObject("btInCount", btInCount);
		mav.addObject("btOutCount", btOutCount);
		mav.addObject("btInSumOverTime", btInSumOverTime);
		mav.addObject("btOutSumOverTime", btOutSumOverTime);
		
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
		
		int totalEmployee = sumOverTime.size() + btInCount + btOutCount;
		mav.addObject("totalEmployee", totalEmployee);
		return mav;
	}
	
	// sow 일일 수정 폼
	@Override
	@GetMapping("/sow/sowModDailyForm.do")
	public ModelAndView sowModDailyForm(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/sow/sowModDailyForm");
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
	@GetMapping("/sow/sowBoardTotal.do")
	public ModelAndView sowBoardTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/sow/sowBoardTotal");
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
	@GetMapping("/sow/sowAddEmployeeForm.do")
	public ModelAndView sowAddEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/sow/sowAddEmployeeForm");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<SowVO> employeeList = sowService.selectEmployeeList(searchArea);
		mav.addObject("employeeList", employeeList);
		return mav;
	}
	
	// 직원 등록 (정보저장)
	@Override
	@PostMapping("/sow/sowAddEmployee.do")
	public ModelAndView sowAddEmployee(@RequestParam("emp_name") String emp_name, @RequestParam("emp_position") String emp_position, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/sow/sowAddEmployeeForm.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		sowService.sowAddEmployee(searchArea, emp_name, emp_position);
		return mav;
	}
	
	// 직원 수정 및 삭제 ajax
	
	@PostMapping("/sow/updateEmployee.do")
	@ResponseBody
	public String updateEmployee(SowVO employee) throws Exception {
		int result = sowDAO.updateEmployee(employee);
		return "ok";
	}
	
	@PostMapping("/sow/deleteEmployee.do")
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
	
	// 출장자 추가 폼
	@Override
	@GetMapping("/sow/sowAddBtEmployeeForm.do")
	public ModelAndView sowAddBtEmployeeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();

		List<SowVO> btInList = new ArrayList<SowVO>();
		String bt_in = "in";
		btInList = sowService.selectBtEmployeeList(searchArea, bt_in);
		
		String bt_out = "out";
		List<SowVO> btOutList = new ArrayList<SowVO>();
		btOutList = sowService.selectBtEmployeeList(searchArea, bt_out);
		
		mav.addObject("btInList", btInList);
		mav.addObject("btOutList", btOutList);
		return mav;
	}
	
	// 출장자 추가
	@Override
	@PostMapping("/sow/sowAddBtEmployee.do")
	public ModelAndView sowAddBtEmployee(@RequestParam("emp_name") String emp_name, @RequestParam("sowDWL_work_name") String sowDWL_work_name, 
			@RequestParam("bt_inout") String bt_inout, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/sow/sowAddBtEmployeeForm.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int result = sowService.sowAddBtEmployee(emp_name, sowDWL_work_name, bt_inout, searchArea);
		
		return mav;
	}
	
	@PostMapping("/sow/modBtEmployee.do")
	@ResponseBody
	public Map<String, Object> modBtEmployee(@RequestParam("emp_name") String emp_name, @RequestParam("sowDWL_work_name") String sowDWL_work_name,
			@RequestParam("login_area") String login_area, @RequestParam("dummyInt") int dummyInt, HttpServletRequest request) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			sowService.modBtEmployee(emp_name, sowDWL_work_name, login_area, dummyInt);
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "error");
		}
		return result;
	}
	
	@PostMapping("/sow/removeBtEmployee.do")
	@ResponseBody
	public Map<String, Object> removeBtEmployee(@RequestParam("dummyInt") int dummyInt) throws Exception {
		Map<String, Object> result = new HashMap<>();
		
		try {
			sowService.removeBtEmployee(dummyInt);
			result.put("result", "success");
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "error");
		}
		return result;
	}
}
