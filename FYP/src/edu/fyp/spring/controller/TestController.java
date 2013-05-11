package edu.fyp.spring.controller;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fyp.bean.Employee;
import edu.fyp.bean.User;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;
@Controller
public class TestController {
	
	@Autowired
	private UserManager userManager;

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
		employee.setUser(user);
		pm.makePersistent(employee);
		pm.close();
		
		
		return userManager.searchEmployeeByFullText("hoi").toString();
		
//		return "Hello World";
	}
	

}
