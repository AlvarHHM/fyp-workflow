package edu.fyp.spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sun.org.apache.xerces.internal.util.URI;

import edu.fyp.bean.User;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;

@SessionAttributes({ "USER" })
@Controller
public class LoginController {
	
	private UserManager userManager;
	
	@Autowired
	public LoginController(UserManager userManager){
		this.userManager = userManager;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST,params = {"userName","password","redirect"})
	public String loginWithRedirect(@ModelAttribute("USER") User user,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("redirect")String url) throws UnsupportedEncodingException {
		
		user = userManager.login(userName, password);
		return "redirect:"+URLDecoder.decode(url, "UTF-8");
	}
	
	@RequestMapping(value = "/login.do", method = RequestMethod.POST, params = {"userName","password"})
	public String login(@ModelAttribute("USER") User user,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,ModelMap map){
		
		user = userManager.login(userName, password);
		if (user != null){
			map.put("USER", user);
			return "redirect:/Client/home.jsp";
		}else
			return "redirect:/Client/login2.html?error=1";
	}
	
	
	
	@RequestMapping(value = "/testLogin.do", method = RequestMethod.GET)
	public @ResponseBody String test() {
		User test = new User();
		test.setUserName("subject01");
		test.setPassword("subject01");
		PMF.get().getPersistenceManager().makePersistent(test);
		
		return userManager.test();
	}
	
	

	@ModelAttribute("USER")
	public User populateUser() {
		return new User();
	}
}
