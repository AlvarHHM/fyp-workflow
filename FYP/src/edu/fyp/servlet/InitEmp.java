package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Employee.Title;
import edu.fyp.bean.User;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.PMF;

public class InitEmp extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//o
		
		//Init Department
		
		Department prh = new Department();
		prh.setDeptId("00002");
		prh.setDeptName("Tsuen Wan - RB");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(prh);
		} finally {
			pm.close();
		}
		//End of init Department
		
		//Init user
		
		User u1 = new User();
		u1.setUserName("autscr");
		u1.setPassword("123456");
		
		User u2 = new User();
		u2.setUserName("autscr23");
		u2.setPassword("123456");
		
		//Init end of user
		
		//Init employee
		
		Employee emp1 =  new Employee();
		emp1.setEmpId("H0000001");
		emp1.setEngSurname("Lam");
		emp1.setEngOtherName("CHONG CHI");
		emp1.setChiSurname("ªL");
		emp1.setChiOthername("²ø«º");
		emp1.setTitle(Title.COOFF);
		emp1.setNickName("JANE");
		emp1.setEmail("autscr@gmail.com");
		emp1.setSuperLevel(200);
		
		Employee emp2 =  new Employee();
		emp2.setEmpId("H0000002");
		emp2.setEngSurname("Lam");
		emp2.setEngOtherName("Peter");
		emp2.setChiSurname("ªL");
		emp2.setChiOthername("©¼¼w");
		emp2.setTitle(Title.COOFF);
		emp2.setNickName("Peter");
		emp2.setEmail("peter@gmail.com");
		emp2.setSuperLevel(400);
		
		emp1.setDepartment(prh.getDeptKey());
		emp2.setDepartment(prh.getDeptKey());
		u1.setEmployee(emp1);
		u2.setEmployee(emp2);
		//end of init employee
		
		//store emp
		
		pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(u1);
		} finally {
			pm.close();
		}
		
		pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(u2);
		} finally {
			pm.close();
		}
		//end of store
	}
}
