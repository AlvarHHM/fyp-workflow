package edu.fyp.spring.controller;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import edu.fyp.bean.node.*;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;

@Controller
public class UserController {
	
	@Autowired
	private UserManager userManager;
	
	
	
	
	@RequestMapping(value="/searchUser.do", method=RequestMethod.GET)
	public @ResponseBody String searechUser(@RequestParam(value="queryString",required=true) String queryString){
		Gson gson = new Gson();
		return gson.toJson(userManager.searchEmployeeByFullText(queryString));

	}
}
