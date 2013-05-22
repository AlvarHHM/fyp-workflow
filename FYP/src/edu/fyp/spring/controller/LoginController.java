package edu.fyp.spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.sun.org.apache.xerces.internal.util.URI;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import edu.fyp.bean.BuilderUser;
import edu.fyp.bean.User;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;

@SessionAttributes({ "USER" })
@Controller
public class LoginController {

	private UserManager userManager;

	@Autowired
	public LoginController(UserManager userManager) {
		this.userManager = userManager;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, params = {
			"userName", "password", "redirect" })
	public String loginWithRedirect(@ModelAttribute("USER") User user,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			@RequestParam("redirect") String url, HttpSession session)
			throws UnsupportedEncodingException {

		user = userManager.login(userName, password);
		if (user != null) {
			session.setAttribute("USER", user);
			session.setAttribute("EMP", user.getEmployee());
			if (user.getEmployee() != null)
				session.setAttribute("DEPT", user.getEmployee().getDepartment());
			return "redirect:" + URLDecoder.decode(url, "UTF-8");
		} else
			return "redirect:/Client/login2.html?error=1";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, params = {
			"userName", "password" })
	public String login(@ModelAttribute("USER") User user,
			@RequestParam("userName") String userName,
			@RequestParam("password") String password, HttpSession session) {

		if ("admin".equals(userName) && "admin".equals(password)) {
			return "redirect:/Admin/showAdminApplicationListServlet";
		}

		user = userManager.login(userName, password);

		if (user != null) {
			session.setAttribute("USER", user);
			session.setAttribute("EMP", user.getEmployee());
			if (user.getEmployee() != null)
				session.setAttribute("DEPT", user.getEmployee().getDepartment());
			return "redirect:/Client/home";
		} else
			return "redirect:/Client/login2.html?error=1";
	}
	
	@RequestMapping("/formbuilder/logout.do")
	public String builderLogout(HttpSession session){
		session.removeAttribute("BUILDERUSER");
		session.removeAttribute("BUILDEREMP");
		session.removeAttribute("BUILDERDEPT");
		return "redirect:/formbulder/login.html";
	}
	
	@RequestMapping("/Client/logout.do")
	public String clientLogout(HttpSession session){
		session.removeAttribute("USER");
		session.removeAttribute("EMP");
		session.removeAttribute("EMP");
		return "redirect:/Client/login2.html";
	}

//	@RequestMapping(value = "/formbuilder/login.do", method = RequestMethod.POST, params = {
//			"userName", "password" })
//	public String loginBuilder(@ModelAttribute("BUILDERUSER") BuilderUser user,
//			@RequestParam("userName") String userName,
//			@RequestParam("password") String password, HttpSession session) {
//		user = userManager.loginBuilder(userName, password);
//		if (user != null) {
//
//			session.setAttribute("BUILDERUSER", user);
//			session.setAttribute("BUILDEREMP", user.getEmployee());
//			if (user.getEmployee() != null)
//				session.setAttribute("BUILDERDEPT", user.getEmployee()
//						.getDepartment());
//			return "redirect:/formbuilder/showBuilderFormListServlet";
//		} else
//			return "redirect:/formbuilder/login.html?error=1";
//	}

	@RequestMapping(value = "/testLogin.do", method = RequestMethod.GET)
	public @ResponseBody
	String test() {
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

//	@ModelAttribute("BUILDERUSER")
//	public BuilderUser populateBuilderUser() {
//		return new BuilderUser();
//	}
}
