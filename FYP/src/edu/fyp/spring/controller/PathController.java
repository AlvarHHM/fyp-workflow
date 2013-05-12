package edu.fyp.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PathController {
	
	@RequestMapping("/path")
	public  ModelAndView path(){
		ModelAndView mav = new ModelAndView("path/pathbuilder");
		return mav;
	}
	
	@RequestMapping("/searchUserPanel")
	public  ModelAndView searchUserPanel(){
		ModelAndView mav = new ModelAndView("path/searchUser");
		return mav;
	}

}
