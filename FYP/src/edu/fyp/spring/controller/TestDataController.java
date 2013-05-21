package edu.fyp.spring.controller;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.fyp.bean.BuilderUser;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.User;
import edu.fyp.bean.Employee.Title;
import edu.fyp.repository.PMF;


@Controller
public class TestDataController {

	@RequestMapping("/initBuilderUser")
	public @ResponseBody String initBuilderUser(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Department prh = new Department();
		prh.setDeptId("09999");
		prh.setDeptName("IT Department - Head Branch");
		pm.makePersistent(prh);
		
		BuilderUser u1 = new BuilderUser();
		u1.setUserName("autscr9999");
		u1.setPassword("123456");
		
		Employee emp1 =  new Employee();
		emp1.setEmpId("H9999999");
		emp1.setEngSurname("CHAN");
		emp1.setEngOtherName("KONG MING");
		emp1.setChiSurname("³¯");
		emp1.setChiOthername("´ä©ú");
		emp1.setTitle(Title.COOFF);
		emp1.setNickName("Alvar");
		emp1.setEmail("autscr0001@gmail.com");
		emp1.setSuperLevel(200);
		emp1.setDepartment(prh.getDeptKey());
		u1.setEmployee(emp1);
		
		pm.makePersistent(u1);
		return "hello";
	}
}
