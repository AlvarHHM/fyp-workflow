package edu.fyp.spring.controller;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fyp.bean.Employee;
import edu.fyp.bean.TestAutoWiredBean;
import edu.fyp.bean.User;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;
@Controller
public class TestController {
	
	@Autowired
	private UserManager userManager;
	
	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping("/test.do")
	public @ResponseBody
	String test() throws Exception {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		User user = new User();
		user.setUserName("mahoihei");
		user.setPassword("mahoihei");
		
		
		
		Employee employee =new Employee();
		employee.setNickName("Alvar");
		employee.setEngSurname("Ma");
		employee.setEngOtherName("Hoi Hei");
		employee.setEmpId("H001");
		employee.updateFullTextSearchIndex();
		user.setEmployee(employee);
		employee.setUser(user);
		pm.makePersistent(user);
		pm.close();
		
		
		return userManager.searchEmployeeByFullText("hoi").toString();
		
//		return "Hello World";
	}
	
	@RequestMapping("/test2")
	public @ResponseBody
	String test2() throws Exception {
		TestAutoWiredBean bean = new TestAutoWiredBean();
		applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
		
		
		
		
		return String.valueOf(bean.getUserManager().searchEmployeeByFullText("ma").toString());
	}
	

}
