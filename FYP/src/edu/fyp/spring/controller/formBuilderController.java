package edu.fyp.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class formBuilderController {


	@RequestMapping("/formbuilder/formBuilder.jsp")
	public @ResponseBody
	String test() throws Exception {

		return "/formbuilder/formBuilder.jsp";
	}
}
