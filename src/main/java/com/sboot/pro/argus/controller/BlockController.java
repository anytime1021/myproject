package com.sboot.pro.argus.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
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
}
