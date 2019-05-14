package com.sterling.seedx.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


public class ErrorController {
private static final Logger log = Logger.getLogger(ErrorController.class);
	
	@RequestMapping(value="/error/404", method=RequestMethod.GET)
	public ModelAndView send404(HttpServletRequest req) {
		
		log.error("404 error occures: " + req.getRequestURL().toString());
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/error/404");
		return mav;
	}
	
}