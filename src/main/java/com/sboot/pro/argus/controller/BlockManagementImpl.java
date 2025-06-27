package com.sboot.pro.argus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

@Controller("blockManagementController")
public class BlockManagementImpl implements BlockManagement {

	@GetMapping("/blockManagement/blockList.do")
	public ModelAndView blockList(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockList");
		return mav;
	}
}
