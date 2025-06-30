package com.sboot.pro.argus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.pro.argus.service.BlockService;
import com.sboot.pro.argus.vo.BlockVO;
import com.sboot.pro.argus.vo.LoginVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller("blockManagementController")
public class BlockControllerImpl implements BlockController {
	
	@Autowired
	BlockService blockService;
	
	@Override
	@GetMapping("/blockManagement/blockList.do")
	public ModelAndView blockList(@RequestParam(value="page", defaultValue="1") int page, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/blockManagement/blockList");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();

		int limit = 20;
		int offset = (page-1) * limit ;
		
		int totalCount = blockService.getBlockCount(searchArea);
		
		List<BlockVO> blockList = blockService.selectBlockList(searchArea, offset, limit);
		
		int totalPage = (int) Math.ceil((double) totalCount / limit);
		
		mav.addObject("blockList", blockList);
		mav.addObject("currentPage", page);
		mav.addObject("totalPage", totalPage);
		return mav;
	}
}
