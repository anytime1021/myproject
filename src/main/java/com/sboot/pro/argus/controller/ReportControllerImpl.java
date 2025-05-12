package com.sboot.pro.argus.controller;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.DTO.DailyReportWorkrate;
import com.sboot.pro.argus.dao.CommonDAO;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.service.CommonService;
import com.sboot.pro.argus.service.ReportService;
import com.sboot.pro.argus.service.SowService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ReportVO;
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
		return Integer.parseInt(str);
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
	@GetMapping("/report/testList2.do")
	public ModelAndView testList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/testList2");
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
		ModelAndView mav = new ModelAndView("redirect:/report/testList2.do");
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

