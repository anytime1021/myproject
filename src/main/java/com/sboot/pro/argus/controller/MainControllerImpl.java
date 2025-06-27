package com.sboot.pro.argus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sboot.pro.argus.AccessLogUtil;
import com.sboot.pro.argus.dao.LoginDAO;
import com.sboot.pro.argus.dao.ReportDAO;
import com.sboot.pro.argus.service.LoginService;
import com.sboot.pro.argus.service.ReportService;
import com.sboot.pro.argus.vo.LoginVO;
import com.sboot.pro.argus.vo.ReportVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller("mainController")
public class MainControllerImpl implements MainController {
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private ReportVO reportVO;
	
	@Autowired
	private ReportDAO reportDAO;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoginVO loginVO;
	
	@Autowired
	private LoginDAO loginDAO;
	
	// 메인 로그인 페이지 접속
	@Override
	@GetMapping("/argus/loginForm.do")
	public ModelAndView ArgusloginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/argus/loginForm");
		return mav;
	}
	
	// 메인 페이지 접속2
	@Override
	@GetMapping("/argus/main2.do")
	public ModelAndView main2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/argus/main2");
		HttpSession session = request.getSession();
		LoginVO login = (LoginVO) session.getAttribute("login");
		String searchArea = login.getLogin_area();
		List<ReportVO> workrateList = new ArrayList<>();
		workrateList = reportService.selectWorkrateList(searchArea);
		mav.addObject("workrateList", workrateList);
		return mav;
	}
	
	// 메인 페이지 접속
	@Override
	@GetMapping("/argus/main.do")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/argus/main");
		return mav;
	}
	
	@Override
	@GetMapping("/report/fronttest.do")
	public ModelAndView fronttest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/report/fronttest");
		return mav;
	}
	
	// 로그인 페이지 접속
	@Override
	@GetMapping("/login/loginForm.do")
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/login/loginForm");
		return mav;
	}
	
	// 로그인
	@Override
	@PostMapping("/login/login.do")
	public ModelAndView login(@RequestParam("login_id") String login_id, @RequestParam("login_pwd") String login_pwd,
			RedirectAttributes rAttr, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/argus/main2");
		loginVO = loginService.login(login_id, login_pwd);
		if(loginVO != null) {
			HttpSession session = request.getSession();
			session.setAttribute("login", loginVO);
			session.setAttribute("logOn", true);
			String action = (String) session.getAttribute("action");
			session.removeAttribute("action");
	        String ip = request.getRemoteAddr();

	        // 로그 저장
	        AccessLogUtil.write(login_id, ip);
			if(action != null) {
				mav.setViewName("redirect:"+action);
			} else {
				mav.setViewName("redirect:/argus/main2.do");
			}
		} else {
			rAttr.addAttribute("result","loginFailed");
			mav.setViewName("redirect:/argus/loginForm.do");
		}
		return mav;
	}
	
	// 로그아웃
	@Override
	@GetMapping("/login/logout.do")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("login");
		session.setAttribute("logOn", false);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/argus/main.do");
		session.invalidate();
		return mav;
	}

	
	
	@GetMapping("/login")
    public String testLog(HttpServletRequest request) {
        String userId = "guest"; // 로그인 안 됐으면 임시값
        String ip = request.getRemoteAddr();

        // 로그 저장
        AccessLogUtil.write(userId, ip);

        return "test"; // test.html로 이동 (없어도 일단 동작함)
    }
	
}
