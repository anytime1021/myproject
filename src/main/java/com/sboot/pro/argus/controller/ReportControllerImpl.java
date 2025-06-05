package com.sboot.pro.argus.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sboot.pro.argus.DTO.BoardType;
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.DTO.DailyReportWorkrate;
import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.dao.SowDAO;
import com.sboot.pro.argus.service.CommonService;
import com.sboot.pro.argus.service.ReportService;
import com.sboot.pro.argus.service.ResultsService;
import com.sboot.pro.argus.service.SowService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ReportVO;
import com.sboot.pro.argus.vo.ResultsVO;
import com.sboot.pro.argus.vo.SowVO;
import com.sboot.pro.argus.vo.WorkingDailyBaseVO;

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
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CommonDAO commonDAO;
	
	@Autowired
	private SowService sowService;
	
	@Autowired
	private SowDAO sowDAO;
	
	@Autowired
	private ResultsService resultsService;
	
	@Autowired
	private WorkingDailyBaseVO workingDailyBaseVO;

	
	// 일일 보고서 게시판 접속
	@Override
	@GetMapping("/report/reportArea.do")
	public ModelAndView reportArea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportArea");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int token = 1;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<WorkingDailyBaseVO> reportListJsp = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("reportListJsp", reportListJsp);
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
		
	    ReportVO HowToWork = reportDAO.selectHTW(searchArea);
	    mav.addObject("HowToWork", HowToWork);
		
		List<ReportVO> addReport_total = new ArrayList<ReportVO>();
		addReport_total = reportService.selectWorkTotal(searchArea);
		mav.addObject("addReport_total", addReport_total);
		return mav;
	}
	
	// 일일 보고서 글쓰기(게시판)
	@Override
	@PostMapping("/report/addDailyReportBoard.do")
	public ModelAndView addDailyReportBoard(@RequestParam("work_date") String work_date, @RequestParam("board_title") String board_title,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		reportDAO.insertWorkrate_board(searchArea, work_date, board_title);
		return mav;
	}
	
	// 일일 보고서 글쓰기(정보저장)
	@Override
	@PostMapping("/report/addDailyReport.do")
	public ModelAndView addReport(@RequestParam(value = "work_name", required =false) String[] work_nameArray,
			@RequestParam(value = "work_amount_HTW1", required = false) String[] work_amount_HTW1Array,
			@RequestParam(value = "work_amount_HTW2", required = false) String[] work_amount_HTW2Array,
			@RequestParam(value = "work_amount_HTW3", required = false) String[] work_amount_HTW3Array,
			@RequestParam(value = "work_amount_HTW4", required = false) String[] work_amount_HTW4Array,
			@RequestParam(value = "work_amount_HTW5", required = false) String[] work_amount_HTW5Array,
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
			for (int i = 0; i < work_amount_HTW1Array.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_name(work_nameArray[i]);
				reportVO.setWork_amount_HTW1(safeParseInt(work_amount_HTW1Array[i]));
				reportVO.setWork_amount_HTW2(safeParseInt(work_amount_HTW2Array[i]));
				reportVO.setWork_amount_HTW3(safeParseInt(work_amount_HTW3Array[i]));
				reportVO.setWork_amount_HTW4(safeParseInt(work_amount_HTW4Array[i]));
				reportVO.setWork_amount_HTW5(safeParseInt(work_amount_HTW5Array[i]));
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
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	
	// 일일 보고서 보기
	@Override
	@GetMapping("/report/reportView.do")
	public ModelAndView reportView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getDailyReportView(work_date, "report/reportView", request);
	}
	
	// 일일 보고서 수정 폼
	@Override
	@GetMapping("/report/modDailyReportForm.do")
	public ModelAndView modDailyReport(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return getDailyReportView(work_date, "report/modDailyReportForm", request);
	}
	
	// 일일 보고서 보기 및 수정 폼
	public ModelAndView getDailyReportView(String work_date, String viewName, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView(viewName);
	    HttpSession session = request.getSession();
	    LoginVO login = (LoginVO) session.getAttribute("login");
	    String searchArea = login.getLogin_area();

	    DailyReportWorkrate dailyReport = reportService.dailyReportWorkrate(searchArea, work_date);
	    List<ReportVO> dailyReportViewMerged = dailyReport.getDailyReportViewMerged();
	    ReportVO totalSum = dailyReport.getTotalSum();
	    ReportVO dailySum = dailyReport.getDailySum();

	    mav.addObject("dailyReportViewMerged", dailyReportViewMerged);
	    mav.addObject("totalSum", totalSum);
	    mav.addObject("dailySum", dailySum);
	    mav.addObject("work_date", work_date);
	    
	    ReportVO HowToWork = reportDAO.selectHTW(searchArea);
	    mav.addObject("HowToWork", HowToWork);
	    
		// 수정사항 체크
		List<ReportVO> getModLog = new ArrayList<ReportVO>();
		int getCountModLog = reportDAO.getCountModLog(searchArea, work_date);
		List<ReportVO> getModDate = reportDAO.getModDate(searchArea, work_date);
		mav.addObject("getCountModLog", getCountModLog);
		if(getCountModLog != 0) {
//				getModLog = reportService.selectModLog(searchArea, work_date);
//				mav.addObject("getModLog", getModLog);
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
			@RequestParam(value = "work_amount_HTW1", required = false) String[] work_amount_HTW1Array,
			@RequestParam(value = "work_amount_HTW2", required = false) String[] work_amount_HTW2Array,
			@RequestParam(value = "work_amount_HTW3", required = false) String[] work_amount_HTW3Array,
			@RequestParam(value = "work_amount_HTW4", required = false) String[] work_amount_HTW4Array,
			@RequestParam(value = "work_amount_HTW5", required = false) String[] work_amount_HTW5Array,
			@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
			@RequestParam("work_date") String work_date,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ModelAndView mav = new ModelAndView("redirect:/report/reportView.do?work_date="+work_date);
			HttpSession session = request.getSession();
			LoginVO login = (LoginVO) session.getAttribute("login");
			String searchArea = login.getLogin_area();
			String login_id = login.getLogin_id();
			System.out.println(work_date);
			List<ReportVO> modReportList = new ArrayList<>();
			for (int i = 0; i < work_amount_HTW1Array.length; i++) {
				ReportVO reportVO = new ReportVO();
				reportVO.setWork_name(work_nameArray[i]);
				reportVO.setWork_amount_HTW1(safeParseInt(work_amount_HTW1Array[i]));
				reportVO.setWork_amount_HTW2(safeParseInt(work_amount_HTW2Array[i]));
				reportVO.setWork_amount_HTW3(safeParseInt(work_amount_HTW3Array[i]));
				reportVO.setWork_amount_HTW4(safeParseInt(work_amount_HTW4Array[i]));
				reportVO.setWork_amount_HTW5(safeParseInt(work_amount_HTW5Array[i]));
				reportVO.setWork_manpower(Integer.parseInt(work_manpowerArray[i]));
				modReportList.add(reportVO);
			}
			reportService.modWorkReportList(searchArea, modReportList, work_date, login_id);
			
			return mav;
		}
		
	
	// 일일 보고서 삭제
	@Override
	@GetMapping("/report/removeDailyReport.do")
	public ModelAndView removeDailyReport(@RequestParam("work_date") String work_date, HttpServletResponse response, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/reportArea.do");
		
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		int result = 0;
		result = reportService.removeDailyReport(searchArea, work_date);
		
		return mav;
	}
	
	
	// 게시판 접속 공용
	@Override
	@GetMapping("/report/reportAreaTotal.do")
	public ModelAndView reportAreaTotal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportAreaTotal");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<WorkingDailyBaseVO> reportListTotalJsp = new ArrayList<WorkingDailyBaseVO>();
		// 월별 작업현황 토큰 1
		int token = 2;
		String tableName = BoardType.fromToken(token).getTableName();
		reportListTotalJsp = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("reportListTotalJsp", reportListTotalJsp);
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
		List<ReportVO> fmonth_list = reportDAO.selectFmonth(searchArea);
		mav.addObject("addReport_total", addReport_total);
		mav.addObject("fmonth_list", fmonth_list);
	
		return mav;
	}
	
	// 작업현황 현장 추가
	@Override
	@PostMapping("/report/addTotalReport.do")
	public ModelAndView addTotalReport(@RequestParam(value = "work_name_total", required = false) String[] work_name_totalArray,
			@RequestParam(value = "work_xray_total", required = false) String[] work_xray_totalArray,
			@RequestParam(value = "work_PAUT_total", required = false) String[] work_PAUT_totalArray,
			@RequestParam(value = "work_charyang_total", required = false) String[] work_charyang_totalArray,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/addTotalReportForm.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		List<ReportVO> addTotalReport = new ArrayList<>();
		for (int i = 0; i < work_name_totalArray.length; i++) {
			ReportVO reportVO = new ReportVO();
			reportVO.setWork_name_total(work_name_totalArray[i]);
			reportVO.setWork_xray_total(work_xray_totalArray[i]);
			reportVO.setWork_PAUT_total(work_PAUT_totalArray[i]);
			reportVO.setWork_charyang_total(work_charyang_totalArray[i]);
			addTotalReport.add(reportVO);
		}
		
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
	
	
		
	
	// -------------------------------------------------------------------------------------------------------------------
	
	// 단가 등록
	@GetMapping("/report/testList.do")
	public ModelAndView testList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/testList");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
        
		String work_date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		CombinedSowDailyWorkLog data = sowService.getCombinedSowDailyWorkLog(searchArea, work_date);
		
		List<SowVO> sowViewList = data.getSowDailyWorkLog();
		List<SowVO> sumOverTime = data.getSumOverTime();
		List<ReportVO> workname = reportDAO.selectWorkTotal(searchArea);
//		sowViewList = reportService.selectViewList(searchArea, work_date);
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		mav.addObject("work_date", work_date);
		mav.addObject("workname", workname);
		mav.addObject("searchArea", searchArea);
		
	    List<ReportVO> list = reportDAO.selectUnitCost(login.getLogin_area());
	    mav.addObject("unitCostList", list);
	    
	    List<ReportVO> workNameList = reportDAO.selectUnitCostWorkName(login.getLogin_area());
	    System.out.println(list);
	    System.out.println(workNameList);
	    mav.addObject("workNameList", workNameList);
		return mav;
	}
	
	// 단가 추가 
	@PostMapping("/report/addPrice.do")
	public ModelAndView addPrice(@ModelAttribute("addPrice") ReportVO addPrice, HttpServletRequest request, HttpServletResponse response) throws Exception {
	    ModelAndView mav = new ModelAndView("redirect:/report/addPrice.do");
		HttpSession session = request.getSession();
	    LoginVO login = (LoginVO) session.getAttribute("login");
	    addPrice.setLogin_area(login.getLogin_area());
	    reportDAO.insertMethodQuantityCost(addPrice);
	    List<ReportVO> list = reportDAO.selectUnitCost(login.getLogin_area());
	    mav.addObject("unitCostList", list);
	    return mav; 
	}
	
	// testList2 이하 업체등록 및 익월예상보고
	@GetMapping("/report/followingMonth.do")
	public ModelAndView testList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/followingMonth");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> fmonth_list = reportDAO.selectFmonth(searchArea);
		mav.addObject("fmonth_list", fmonth_list);
		return mav;
	}
	
	@PostMapping("/report/numberToHangul")
	@ResponseBody
	public String numberToHangul(@RequestBody Map<String, Object> data) {
	    String amountStr = String.valueOf(data.get("amount"));
	    BigDecimal amount = new BigDecimal(amountStr);
	    return convertToHangul(amount.longValue());
	}
	
	public String convertToHangul(long amount) {
	    if (amount == 0) return "영원";

	    final String[] unit1 = {"", "만", "억", "조", "경"};
	    final String[] unit2 = {"", "십", "백", "천"};
	    final String[] number = {"", "일", "이", "삼", "사", "오", "육", "칠", "팔", "구"};

	    StringBuilder result = new StringBuilder();
	    int unit1Index = 0;

	    while (amount > 0) {
	        int part = (int)(amount % 10000);
	        amount /= 10000;

	        if (part != 0) {
	            StringBuilder temp = new StringBuilder();
	            int digit, unit2Index = 0;
	            while (part > 0) {
	                digit = part % 10;
	                if (digit != 0)
	                    temp.insert(0, number[digit] + unit2[unit2Index]);
	                part /= 10;
	                unit2Index++;
	            }
	            result.insert(0, temp.toString() + unit1[unit1Index]);
	        }
	        unit1Index++;
	    }

	    result.append("원");
	    return result.toString();
	}
	
	@PostMapping("/report/addFollowingMonth.do")
	public ModelAndView addFollowingMonth(@RequestParam("fmonth_name") String fmonth_name, @RequestParam("fmonth_profits") BigDecimal fmonth_profits,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("redirect:/report/followingMonth.do");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> fmonth_list = new ArrayList<ReportVO>();
		fmonth_list = reportService.selectFmonth(fmonth_name, fmonth_profits, searchArea);
		mav.addObject("fmonth_list", fmonth_list);
		return mav;
	}
	
	@PostMapping("/report/updateFmonth.do")
	@ResponseBody
	public String updateFmonth(ReportVO vo) throws Exception {
	    int result = reportDAO.updateFmonth(vo);
	    return "ok";
	}

	@PostMapping("/report/deleteFmonth.do")
	@ResponseBody
	public String deleteFmonth(ReportVO vo) throws Exception {
		reportDAO.deleteFmonth(vo);
	    return "ok";
	}
	
	// 여기가지 익월 예상 보고
	@GetMapping("/report/addEmployee.do")
	public ModelAndView testList3(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/testList3");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> fmonth_list = reportDAO.selectFmonth(searchArea);
		mav.addObject("fmonth_list", fmonth_list);
		return mav;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/report/mixReportTest.do")
	public ModelAndView mixReportTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/mixReportTest");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
				
		int token = 4;
		
		String tableName = BoardType.fromToken(token).getTableName();
		List<WorkingDailyBaseVO> reportListJsp = commonService.reportListTotalJava(searchArea, tableName);
		mav.addObject("reportListJsp", reportListJsp);
		
		return mav;
	}
	
	@GetMapping("/report/addDailyReportFormMixed.do")
	public ModelAndView addDailyReportFormMixed(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/report/addDailyReportFormMixed");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 마지막 작성 날짜 검색
		String work_date_before = reportDAO.selectWorkdate(searchArea);
		if (work_date_before == null) {
			LocalDate today = LocalDate.now();
			work_date_before = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date = LocalDate.parse(work_date_before, formatter);
			LocalDate previousDate = date.minusDays(1);
			work_date_before = previousDate.format(formatter);
		}
		
		String work_date = work_date_before;

		// 1. 작업 현황
		ReportVO HowToWork = reportDAO.selectHTW(searchArea);
	    mav.addObject("HowToWork", HowToWork);
		
	    List<ReportVO> workrateFormBefore = reportService.workrateFormBefore(searchArea, work_date);
		mav.addObject("workrateFormBefore", workrateFormBefore);
		
		List<ReportVO> selectFmonth = reportDAO.selectFmonth(searchArea);
		mav.addObject("selectFmonth", selectFmonth);
		
		// 2. 근무 현황
		CombinedSowDailyWorkLog data = sowService.getCombinedSowDailyWorkLog(searchArea, work_date);
		
		List<SowVO> sowViewList = data.getSowDailyWorkLog();
		List<SowVO> sumOverTime = data.getSumOverTime();
		
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		
		// 출장자 목록
		List<SowVO> btInList = new ArrayList<SowVO>();
		String bt_in = "in";
		btInList = sowService.selectBtEmployeeList(searchArea, bt_in);
		
		String bt_out = "out";
		List<SowVO> btOutList = new ArrayList<SowVO>();
		btOutList = sowService.selectBtEmployeeList(searchArea, bt_out);
		
		mav.addObject("btInList", btInList);
		mav.addObject("btOutList", btOutList);
		
		// 출장자 근무시간
		List<Integer> btInHoursList = new ArrayList<>();
		for (int i=0; i<btInList.size(); i++) {
			btInHoursList.add(8);
		}
		List<Integer> btOutHoursList = new ArrayList<>();
		for (int i=0; i<btOutList.size(); i++) {
			btOutHoursList.add(8);
		}
		
		mav.addObject("btInHoursList", btInHoursList);
		mav.addObject("btOutHoursList", btOutHoursList);

		// 출장자 전날 데이터
		List<SowVO> btInListData = new ArrayList<>();
		btInListData = sowService.selectBusinessTrip(searchArea, "in", work_date);
		
		List<SowVO> btOutListData = new ArrayList<>();
		btOutListData = sowService.selectBusinessTrip(searchArea, "out", work_date);
		
		ObjectMapper objectMapper = new ObjectMapper();
		Iterator<SowVO> iterator = btInListData.iterator();

		int btInCount = sowService.countBtViewList(searchArea, bt_in, work_date);
		int btOutCount = sowService.countBtViewList(searchArea, bt_out, work_date);

		mav.addObject("btInListData", btInListData);
		mav.addObject("btOutListData", btOutListData);
		mav.addObject("btInCount", btInCount);
		mav.addObject("btOutCount", btOutCount);
		
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
			
		mav.addObject("fmonthName", fmonthName); 
		mav.addObject("empList", empList);
		mav.addObject("searchArea", searchArea);
		mav.addObject("hoursList", hoursList);
		
		// 3. 실적
		List<ReportVO> fmonth_list = reportDAO.selectFmonth(searchArea);
		mav.addObject("fmonth_list", fmonth_list);
		return mav;
	}
	
	@PostMapping("/report/addDailyReportMixed.do")
	public ModelAndView addDailyReportMixed(@RequestParam("work_date") String work_date, @RequestParam("board_title") String board_title,
		@RequestParam(value = "work_name", required =false) String[] work_nameArray,
		@RequestParam(value = "work_amount_HTW1", required = false) String[] work_amount_HTW1Array,
		@RequestParam(value = "work_amount_HTW2", required = false) String[] work_amount_HTW2Array,
		@RequestParam(value = "work_amount_HTW3", required = false) String[] work_amount_HTW3Array,
		@RequestParam(value = "work_amount_HTW4", required = false) String[] work_amount_HTW4Array,
		@RequestParam(value = "work_amount_HTW5", required = false) String[] work_amount_HTW5Array,
		@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
		@RequestParam(value = "work_xray", required = false) String[] work_xrayArray,
		@RequestParam(value = "work_PAUT", required = false) String[] work_PAUTArray,
		@RequestParam(value = "work_charyang", required = false) String[] work_charyangArray,
		@RequestParam(value = "fmonth_num", required = false) int[] fmonth_numArray,
		@RequestParam(value = "sowDWL_name", required=false) String[] sowDWL_nameArray,
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
		@RequestParam(value = "fmonth_name", required = false) String[] fmonth_nameArray,
		@RequestParam(value = "fmonth_profits", required = false) String[] fmonth_profitsArray,
		@RequestParam(value = "results_dailyprofits", required = false) String[] results_dailyprofitsArray,
		@RequestParam(value = "note", required = false) String[] noteArray,
		HttpServletRequest request) throws Exception {
		// 세션값받기
		ModelAndView mav = new ModelAndView("redirect:/report/mixReportTest.do");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 1. 작업현황
		List<ReportVO> workReportList = new ArrayList<>();
		for (int i = 0; i < work_amount_HTW1Array.length; i++) {
			ReportVO reportVO = new ReportVO();
			reportVO.setWork_name(work_nameArray[i]);
			reportVO.setWork_amount_HTW1(safeParseInt(work_amount_HTW1Array[i]));
			reportVO.setWork_amount_HTW2(safeParseInt(work_amount_HTW2Array[i]));
			reportVO.setWork_amount_HTW3(safeParseInt(work_amount_HTW3Array[i]));
			reportVO.setWork_amount_HTW4(safeParseInt(work_amount_HTW4Array[i]));
			reportVO.setWork_amount_HTW5(safeParseInt(work_amount_HTW5Array[i]));
			reportVO.setWork_manpower(safeParseInt(work_manpowerArray[i]));
			reportVO.setWork_xray(work_xrayArray[i]);
			reportVO.setWork_PAUT(work_PAUTArray[i]);
			reportVO.setWork_charyang(work_charyangArray[i]);
			reportVO.setWork_date(work_date);
			reportVO.setFmonth_num(fmonth_numArray[i]);
			workReportList.add(reportVO);
		}

		reportService.addWorkReportList(searchArea, workReportList, board_title);
		
		int result = reportService.addReportMixed(searchArea, board_title, work_date);

		// 2. 근무현황
		int forCounting = sowDAO.countNameLength(searchArea);
		
		List<SowVO> sowDailyWorkLogList = new ArrayList<>();
		for (int i = 0; i < forCounting; i++) {
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

		for (int i = 0; i < inCounting; i++) {
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
		
		// 3. 실적
		List<ResultsVO> addResultsList = new ArrayList<>();
		for (int i = 0; i < fmonth_nameArray.length; i++) {
			ResultsVO resultsVO = new ResultsVO();
			resultsVO.setFmonth_name(fmonth_nameArray[i]);
			resultsVO.setFmonth_profits(new BigDecimal(fmonth_profitsArray[i]));
			resultsVO.setResults_dailyprofits(safeParseDecimal(results_dailyprofitsArray[i]));
			resultsVO.setNote(noteArray[i]);
			addResultsList.add(resultsVO);
		}
		
		int boardResult = resultsService.addResultsBoard(searchArea, work_date);
		int listResult = resultsService.addResultsList(searchArea, work_date, addResultsList);
		
		return mav;
	}
	// 추후 리팩토링 예정
//	@PostMapping("/report/addDailyReportMixed.do")
//	public ModelAndView addDailyReportMixed(@ModelAttribute DailyReportListDTO reportDTO, HttpServletRequest request) {
//		
//		List<ReportVO> reportList = reportDTO.getReportList();
//		List<SowVO> sowList = reportDTO.getSowList();
//		List<ResultsVO> resultsList = reportDTO.getResultsList();
//		
//		for (ReportVO vo : reportList) {
//			System.out.println(vo.getWork_name());
//		}
//		
//		for (SowVO vo : sowList) {
//			System.out.println(vo.getSowDWL_name());
//		}
//		
//		for (ResultsVO vo : resultsList) {
//			System.out.println(vo.getFmonth_name());
//		}
//		
//		return new ModelAndView("redirect:/report/mixReportTest.do");
//	}
	
	private int nullReturnZero(String Array) {
		try {
			return (Array != null && !Array.trim().isEmpty()) ? Integer.parseInt(Array.trim()) : 0;
		} catch (NumberFormatException e) {
			return 0;
		}
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
	
	@GetMapping("/report/reportViewMixed.do")
	public ModelAndView reportViewMixed(@RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/report/reportViewMixed");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 1. 작업현황
		DailyReportWorkrate dailyReport = reportService.dailyReportWorkrate(searchArea, work_date);
	    List<ReportVO> dailyReportViewMerged = dailyReport.getDailyReportViewMerged();
	    ReportVO totalSum = dailyReport.getTotalSum();
	    ReportVO dailySum = dailyReport.getDailySum();

	    mav.addObject("dailyReportViewMerged", dailyReportViewMerged);
	    mav.addObject("totalSum", totalSum);
	    mav.addObject("dailySum", dailySum);
	    mav.addObject("work_date", work_date);
	    
	    ReportVO HowToWork = reportDAO.selectHTW(searchArea);
	    mav.addObject("HowToWork", HowToWork);
	    
		// 추가시간 총합 return
		String start_date = work_date.substring(0,7) + "-01";
		int sumOvertime = sowDAO.sumOvertime(searchArea, start_date, work_date);
		mav.addObject("sumOvertime", sumOvertime);
	    
	    // 2. 근무현황
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
		
//		List<String> shiftNames = List.of("교육", "출장(입)", "출장(출)", "경조", "시험", "연차", "병가", "훈련", "기타", "비고");
//		mav.addObject("shiftNames", shiftNames);

		mav.addObject("shiftType", shiftType);
		
		// 날짜
		mav.addObject("work_date", work_date);
		
		int totalEmployee = sumOverTime.size() + btInCount + btOutCount;
		mav.addObject("totalEmployee", totalEmployee);
		
		// 3. 실적
		List<ResultsVO> resultsList = new ArrayList<>();
		resultsList = resultsService.selectResultsList(searchArea, work_date);
		
		mav.addObject("resultsList", resultsList);
		
		ResultsVO resultsSum = new ResultsVO();
		resultsSum = resultsService.selectResultsSum(searchArea, work_date);
		
		mav.addObject("resultsSum", resultsSum);
		mav.addObject("work_date", work_date);
		return mav;
	}
	
	@GetMapping("/report/modDailyReportFormMixed.do")
	public ModelAndView modDailyReportFormMixed(@RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/report/modDailyReportFormMixed");
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		
		// 1. 작업 현황
		ReportVO HowToWork = reportDAO.selectHTW(searchArea);
	    mav.addObject("HowToWork", HowToWork);
		
	    List<ReportVO> workrateFormBefore = reportService.workrateFormBefore(searchArea, work_date);
		mav.addObject("workrateFormBefore", workrateFormBefore);
		
		List<ReportVO> selectFmonth = reportDAO.selectFmonth(searchArea);
		mav.addObject("selectFmonth", selectFmonth);
		
		// 2. 근무 현황
		CombinedSowDailyWorkLog data = sowService.getCombinedSowDailyWorkLog(searchArea, work_date);
		
		List<SowVO> sowViewList = data.getSowDailyWorkLog();
		List<SowVO> sumOverTime = data.getSumOverTime();
		
		mav.addObject("sowViewList", sowViewList);
		mav.addObject("sumOverTime", sumOverTime);
		
		// 출장자 목록
		List<SowVO> btInList = new ArrayList<SowVO>();
		String bt_in = "in";
		btInList = sowService.selectBtEmployeeList(searchArea, bt_in);
		
		String bt_out = "out";
		List<SowVO> btOutList = new ArrayList<SowVO>();
		btOutList = sowService.selectBtEmployeeList(searchArea, bt_out);
		
		mav.addObject("btInList", btInList);
		mav.addObject("btOutList", btOutList);
		
		// 출장자 근무시간
		List<Integer> btInHoursList = new ArrayList<>();
		for (int i=0; i<btInList.size(); i++) {
			btInHoursList.add(8);
		}
		List<Integer> btOutHoursList = new ArrayList<>();
		for (int i=0; i<btOutList.size(); i++) {
			btOutHoursList.add(8);
		}
		
		mav.addObject("btInHoursList", btInHoursList);
		mav.addObject("btOutHoursList", btOutHoursList);

		// 출장자 전날 데이터
		List<SowVO> btInListData = new ArrayList<>();
		btInListData = sowService.selectBusinessTrip(searchArea, "in", work_date);
		
		List<SowVO> btOutListData = new ArrayList<>();
		btOutListData = sowService.selectBusinessTrip(searchArea, "out", work_date);

		int btInCount = sowService.countBtViewList(searchArea, bt_in, work_date);
		int btOutCount = sowService.countBtViewList(searchArea, bt_out, work_date);

		mav.addObject("btInListData", btInListData);
		mav.addObject("btOutListData", btOutListData);
		mav.addObject("btInCount", btInCount);
		mav.addObject("btOutCount", btOutCount);
		
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
			
		mav.addObject("fmonthName", fmonthName); 
		mav.addObject("empList", empList);
		mav.addObject("searchArea", searchArea);
		mav.addObject("hoursList", hoursList);
		
		// 3. 실적
		List<ReportVO> fmonth_list = reportDAO.selectFmonth(searchArea);
		mav.addObject("fmonth_list", fmonth_list);
		
		List<ResultsVO> results_list = resultsService.selectResultsList(searchArea, work_date);
		mav.addObject("results_list", results_list);
		
		// 날짜 및 기타 수정폼 기능 - DAO직행
		mav.addObject("work_date", work_date);
		
		String board_title = reportDAO.selectBoardTitle(searchArea, work_date);
		mav.addObject("board_title", board_title);
		
		return mav;
	}
	
	@PostMapping("/report/modDailyReportMixed.do")
	public ModelAndView modDailyReportMixed(@RequestParam(value = "work_date") String work_date, @RequestParam("board_title") String board_title,
		@RequestParam(value = "work_name", required = false) String[] work_nameArray,
		@RequestParam(value = "work_amount_HTW1", required = false) String[] work_amount_HTW1Array,
		@RequestParam(value = "work_amount_HTW2", required = false) String[] work_amount_HTW2Array,
		@RequestParam(value = "work_amount_HTW3", required = false) String[] work_amount_HTW3Array,
		@RequestParam(value = "work_amount_HTW4", required = false) String[] work_amount_HTW4Array,
		@RequestParam(value = "work_amount_HTW5", required = false) String[] work_amount_HTW5Array,
		@RequestParam(value = "work_manpower", required = false) String[] work_manpowerArray,
		@RequestParam(value = "work_xray", required = false) String[] work_xrayArray,
		@RequestParam(value = "work_PAUT", required = false) String[] work_PAUTArray,
		@RequestParam(value = "work_charyang", required = false) String[] work_charyangArray,
		@RequestParam(value = "fmonth_num", required = false) int[] fmonth_numArray,
		@RequestParam(value = "sowDWL_name", required=false) String[] sowDWL_nameArray,
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
		@RequestParam(value = "fmonth_name", required = false) String[] fmonth_nameArray,
//		@RequestParam(value = "fmonth_profits", required = false) String[] fmonth_profitsArray,
		@RequestParam(value = "results_dailyprofits", required = false) String[] results_dailyprofitsArray,
		@RequestParam(value = "results_fmonth_num", required = false) String[] results_fmonth_numArray,
		@RequestParam(value = "note", required = false) String[] noteArray,
		HttpServletRequest request) throws Exception {
		// 세션값받기
		ModelAndView mav = new ModelAndView("redirect:/report/reportViewMixed.do?work_date=" + work_date);
		LoginVO login = (LoginVO) request.getAttribute("login");
		String searchArea = login.getLogin_area();
		String login_id = login.getLogin_id();
		
		// 1. 작업현황
		List<ReportVO> modReportList = new ArrayList<>();
		for (int i = 0; i < work_amount_HTW1Array.length; i++) {
			ReportVO reportVO = new ReportVO();
			reportVO.setWork_name(work_nameArray[i]);
			reportVO.setWork_amount_HTW1(safeParseInt(work_amount_HTW1Array[i]));
			reportVO.setWork_amount_HTW2(safeParseInt(work_amount_HTW2Array[i]));
			reportVO.setWork_amount_HTW3(safeParseInt(work_amount_HTW3Array[i]));
			reportVO.setWork_amount_HTW4(safeParseInt(work_amount_HTW4Array[i]));
			reportVO.setWork_amount_HTW5(safeParseInt(work_amount_HTW5Array[i]));
			reportVO.setWork_manpower(safeParseInt(work_manpowerArray[i]));
			reportVO.setWork_xray(work_xrayArray[i]);
			reportVO.setWork_PAUT(work_PAUTArray[i]);
			reportVO.setWork_charyang(work_charyangArray[i]);
			reportVO.setWork_date(work_date);
			reportVO.setFmonth_num(fmonth_numArray[i]);
			modReportList.add(reportVO);
		}
		reportService.modWorkReportList(searchArea, modReportList, work_date, login_id);
		
		int result = reportService.modReportMixed(searchArea, board_title, work_date);
				
		// 2. 근무현황
		int forCounting = sowDAO.countNameLength(searchArea);
		
		List<SowVO> sowDailyWorkLogList = new ArrayList<>();
		for (int i = 0; i < forCounting; i++) {
			SowVO sowvo = new SowVO();
			sowvo.setSowDWL_name(sowDWL_nameArray[i]);
			sowvo.setSowDWL_work_name(sowDWL_work_nameArray[i]);
			sowvo.setSowDWL_shift(sowDWL_shiftArray[i]);
			sowvo.setSowDWL_hours(nullReturnZero(sowDWL_hoursArray[i]));
			sowvo.setSowDWL_overtime(nullReturnZero(sowDWL_overtimeArray[i]));
			sowvo.setEmp_num(nullReturnZero(emp_numArray[i]));
			sowDailyWorkLogList.add(sowvo);
		}
		sowService.sowModDailyWorkLogList(sowDailyWorkLogList, searchArea, login_id, work_date);

		int inCounting = sowDAO.countBtNameLength(searchArea, "in");
		int outCounting = sowDAO.countBtNameLength(searchArea, "out");
		
		List<SowVO> sowBusinessTripIn = new ArrayList<>();

		for (int i = 0; i < inCounting; i++) {
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
			result = sowService.sowModBusinessTrip(sowBusinessTripIn, searchArea, login_id, work_date);
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
			result = sowService.sowModBusinessTrip(sowBusinessTripOut, searchArea, login_id, work_date);
		}
		
		// 3. 실적
		List<ResultsVO> modResultsList = new ArrayList<>();
		for (int i = 0; i < fmonth_nameArray.length; i++) {
			ResultsVO resultsVO = new ResultsVO();
			resultsVO.setFmonth_name(fmonth_nameArray[i]);
//			resultsVO.setFmonth_profits(new BigDecimal(fmonth_profitsArray[i]));
			resultsVO.setResults_dailyprofits(safeParseDecimal(results_dailyprofitsArray[i]));
			resultsVO.setNote(noteArray[i]);
			resultsVO.setFmonth_num(nullReturnZero(results_fmonth_numArray[i]));
			modResultsList.add(resultsVO);
		}
		
		int listResult = resultsService.modResultsList(searchArea, login_id, work_date, modResultsList);
		
		return mav;
	}
}




// 일별 보고서 보기
//@Override
//@GetMapping("/report/reportView.do")
//public ModelAndView reportView(@RequestParam("work_date") String work_date, HttpServletRequest request, HttpServletResponse response) throws Exception
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
////	dailyReport = reportService.dailyReportInfo(searchArea, work_date);
//	
//	String searchDate = work_date.substring(0,7);
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
//	mav.addObject("work_date", work_date);
//	mav.addObject("mergedList", mergedList);
//	mav.addObject("addReport_sum", addReport_sum);
//	return mav;
//}

