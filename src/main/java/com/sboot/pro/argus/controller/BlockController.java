package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.vo.BlockVO;

import jakarta.servlet.http.HttpServletRequest;

public interface BlockController {

	// 블럭 리스트
	public ModelAndView blockList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;

	// 블럭 상세보기
	public ModelAndView blockView(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception;

	// 블럭 추가 폼
	public ModelAndView addBlockForm(HttpServletRequest request) throws Exception;
	
	// 블럭 추가
	public ModelAndView addBlock(@ModelAttribute("addBlockForm") BlockVO addblockForm, HttpServletRequest request) throws Exception;

	// 블럭 수정 폼
	public ModelAndView modBlockForm(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception;

	// 블럭 수정
	public ModelAndView modBlock(@ModelAttribute("modBlock") BlockVO modBlock, HttpServletRequest request) throws Exception;

	// 블럭 삭제
	public ModelAndView removeBlock(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest requst) throws Exception;
	
	// 블럭 대여 등록 폼
	public ModelAndView moveBlockForm(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception;
	
	// 블럭 대여
	public ModelAndView moveBlock(@ModelAttribute("moveBlockList") BlockVO moveBlockList, HttpServletRequest request) throws Exception;
	
	// 블럭 외부 반출 폼
	public ModelAndView expertBlockForm(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception;

	// 블럭 외부 반출
	public ModelAndView expertBlock(@ModelAttribute("expertBlock") BlockVO expertBlock, HttpServletRequest request) throws Exception;

	// 대여한 블럭 리스트
	public ModelAndView blockRentalList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;
	
	// 블럭 반납 폼
	public ModelAndView returnBlockForm(@RequestParam("app_num_Str") String app_num_str, HttpServletRequest request) throws Exception;
	
	// 블럭 반출 반납 폼
	public ModelAndView returnExpertBlockForm(@RequestParam("app_num") int app_num, HttpServletRequest request) throws Exception;
	
	// 블럭 반납
	public ModelAndView returnBlockApproval(@ModelAttribute("returnBlockApproval") BlockVO returnBlockApproval, HttpServletRequest request) throws Exception;
	public ModelAndView returnApproval(@RequestParam("app_num") int app_num, @RequestParam("app_isError") String app_isError, HttpServletRequest request) throws Exception;
	
	// 블럭 반납 - 구
	public ModelAndView returnBlock(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception;
	
	// 블럭 이동 기록
	public ModelAndView blockMoveList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;

	// 블럭 검색
	public ModelAndView searchList(@RequestParam(value="page", defaultValue="1") int page, @RequestParam("searchType") String searchType, @RequestParam("searchQuery") String searchQuery, @RequestParam("token") String token, HttpServletRequest request) throws Exception;

	// 전체 블럭 리스트
	public ModelAndView blockTotalList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;

	// 승인 대기 리스트
	public ModelAndView blockApproval(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;

	// 반출 승인 대기 리스트
	public ModelAndView expertApproval(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;

	// 이동 보고서 상세보기
	public ModelAndView blockApprovalView(@RequestParam("app_num_Str") String app_num_Str, HttpServletRequest request) throws Exception;

	// 반출 이동 보고서 상세보기
	public ModelAndView blockExpertApprovalView(@RequestParam("app_num_Str") String app_num_Str, HttpServletRequest request) throws Exception;
	
	// 이동 승인
	public ModelAndView updateApproval(@RequestParam("app_num") int app_num, @RequestParam("app_isError") String app_isError, HttpServletRequest request) throws Exception;

	// 반출 이동 승인
	public ModelAndView updateExpertApproval(@RequestParam("app_num") int app_num, @RequestParam("app_isError") String app_isError, @RequestParam("token") String token_Str, 
			@RequestParam("app_rcv_name") String app_rcv_name, HttpServletRequest request) throws Exception;
	
	// 반출 이동 사인 업로드
	@PostMapping("/blockManagement/updateExpertSign.do")
	public ModelAndView updateExpertSign(@RequestParam("app_num") int app_num, @RequestParam("expertSign") MultipartFile expertSign, @RequestParam("app_rcv_area") String app_rcv_area, HttpServletRequest request) throws Exception;
	
	// 이동 거절
	public ModelAndView updateRejection(@RequestParam("app_num") int app_num, HttpServletRequest request) throws Exception;
	
	// 반출 거절
	public ModelAndView updateExpertRejection(@RequestParam("app_num") int app_num, @RequestParam("app_isError") String app_isError, @RequestParam("token") String token_Str, 
			@RequestParam("app_rcv_name") String app_rcv_name, @RequestParam("app_type") String app_type, HttpServletRequest request) throws Exception;
	
	// 블럭 스펙 추가 폼
	public ModelAndView addBlockSpecForm(@RequestParam("df_idNumber") String df_idNumber) throws Exception;
	
	// 블럭 스펙 추가
	public ModelAndView addBlockSpec(@RequestParam("df_idNumber") String df_idNumber, @RequestParam("files") MultipartFile[] files, HttpServletRequest request) throws Exception;
	
	// 블럭 스펙 보기
	public ModelAndView blockSpecView(@RequestParam("df_idNumber") String df_idNumber, HttpServletRequest request) throws Exception;

	// 블럭 스펙 삭제
	public ModelAndView removeBlockSpec(@RequestParam("df_idNumber") String df_idNumber) throws Exception;

	// 블럭 점검 리스트
	public ModelAndView blockInspectionList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;
	
	// 블럭 점검 폼
	public ModelAndView addInspectionForm(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception;
	
	// 블럭 점검 추가 (정보저장)
	public ModelAndView addInspection(@RequestParam("bib_title") String bib_title,
			@RequestParam(value = "df_idNumber", required = false) String[] df_idNumberArray,
			@RequestParam(value = "bil_status", required = false) String[] bil_statusArray,
			HttpServletRequest request) throws Exception;
	
	// 블럭 점검 보기
	public ModelAndView blockInspectionView(@RequestParam("bib_num") int bib_num) throws Exception;
	
	// 블럭 점검 삭제
	public ModelAndView removeInspectionView(@RequestParam("bib_num") int bib_num) throws Exception;
	
	// 블럭 점검 이력 보기
	public ModelAndView inspectionHistory(@RequestParam(value="page", defaultValue = "1") int page, @RequestParam("df_idNumber") String df_idNumber) throws Exception;

	// 블럭 제작 요청 폼
	public ModelAndView createBlockList(@RequestParam(value="page", defaultValue = "1") int page, HttpServletRequest request) throws Exception;
	
	// 블럭 제작 요청 폼
	public ModelAndView createBlockForm(HttpServletRequest request) throws Exception;
}
