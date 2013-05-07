package edu.fyp.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class showClientApplicationListController {


	@RequestMapping("/Client/showClientApplicationList.jsp")
	public @ResponseBody
	String test() throws Exception {

		return "/Client/showClientApplicationList.jsp";
	}
}
