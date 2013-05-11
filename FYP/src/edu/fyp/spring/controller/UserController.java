package edu.fyp.spring.controller;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	
	@RequestMapping("/user")
	public @ResponseBody String test(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApproveNode an1 = new ApproveNode();
		ApproveNode an2 = new ApproveNode();
		pm.makePersistent(an1);
		pm.makePersistent(an2).setNextNode(an1.getNodeID());
		
		
		
		return "Hello World";
	}
	
	@RequestMapping(value="/user/search", params="{queryString}")
	public @ResponseBody String searechUser(@RequestParam String queryString){
		Gson gson = new Gson();
		return gson.toJson(userManager.searchEmployeeByFullText(queryString));

	}
}
