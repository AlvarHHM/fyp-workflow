package edu.fyp.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PathController {
	@RequestMapping("/formbuilder/pathbuilder")
	public String pathEditor(){
		
		
		return "/formbuilder/path/pathbuilder.jsp";
	}
}
