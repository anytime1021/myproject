package com.sboot.pro.argus.controller;

import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface ResultsController {
	public ModelAndView resultsBoard(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
