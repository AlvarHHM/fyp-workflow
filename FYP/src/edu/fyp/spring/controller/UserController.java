package edu.fyp.spring.controller;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fyp.bean.node.*;
import edu.fyp.repository.PMF;

@Controller
public class UserController {
	PersistenceManager pm = PMF.get().getPersistenceManager();
	@RequestMapping("/test")
	public @ResponseBody String test(){
		
		ApproveNode an1 = new ApproveNode();
		ApproveNode an2 = new ApproveNode();
		pm.makePersistent(an1);
		pm.makePersistent(an2).setNextNode(an1.getNodeID());
		
		
		
		return "Hello World";
	}
}
