package edu.fyp.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import edu.fyp.bean.User;
import edu.fyp.manager.UserManager;

@SessionAttributes({ "USER" })
@Controller
public class LoginController {
	
	private UserManager userManager;
	
	@Autowired
	public LoginController(UserManager userManager){
		this.userManager = userManager;
	}

//	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
//	public String login(@ModelAttribute("USER") User user,
//			@RequestParam("userName") String userName,
//			@RequestParam("password") String password) {
//		return null;
//	}
	
	@RequestMapping(value = "/testLogin.do", method = RequestMethod.GET)
	public @ResponseBody String test() {
		return userManager.test();
	}
	
	

	@ModelAttribute("USER")
	public User populateUser() {
		return new User();
	}
}
