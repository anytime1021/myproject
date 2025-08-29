package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.DTO.CombinedSowDailyWorkLog;
import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ReportController {
	// 일일 보고서 게시판 접속
	public ModelAndView reportArea(@RequestParam(value="page", defaultValue = "1") int page, HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	// 일일 보고서 글쓰기(게시판)
	public ModelAndView addDailyReportBoard(@RequestParam("work_date") String work_date, @RequestParam("board_title") String board_title,
			HttpServletRequest request, HttpServletResponse response) throws Exception;

	// 일일 보고서 추가 폼
	@GetMapping("/report/addDailyReportForm.do")
	public ModelAndView addDailyReportForm(HttpServletRequest request) throws Exception;
	
	// 일일 보고서 추가
	public ModelAndView addDailyReport(@RequestParam("work_date") String work_date, 
		@RequestParam("weather") String weather,
		@RequestParam("board_title") String board_title,
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
		@RequestParam(value = "results_fmonth_num", required = false) String[] results_fmonth_numArray,
		HttpServletRequest request) throws Exception;
	
	// 일일 보고서 보기
	public ModelAndView reportView(@RequestParam("board_num") int board_num, @RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception;
	
	// 일일 보고서 수정폼
	public ModelAndView modDailyReportForm(@RequestParam("board_num") int board_num, @RequestParam("area") String searchArea, @RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception;
	
	// 일일 보고서 수정
	public ModelAndView modDailyReport(@RequestParam(value = "work_date") String work_date, 
		@RequestParam("weather") String weather,
		@RequestParam("board_title") String board_title,
		@RequestParam("area") String searchArea,
		@RequestParam("board_num") int board_num,
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
		HttpServletRequest request) throws Exception;
	
	// 일일 보고서 삭제
	public ModelAndView deleteDailyReport(@RequestParam("work_date") String work_date, HttpServletRequest request) throws Exception;

	///////////////////////////////////////////////////////////////////////////////////////////////

}
