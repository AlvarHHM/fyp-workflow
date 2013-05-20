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
import edu.fyp.search.SearchEmployeeUtil;

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
		prh.setDeptName("Tsuen Wan - Riviera Branch");
		
		Department epc5 = new Department();
		epc5.setDeptId("00003");
		epc5.setDeptName("Tseung Kwan O - East Point City Branch (2)");
		
		Department wks = new Department();
		wks.setDeptId("00004");
		wks.setDeptName("Ma On Shan - Wu Kai Sha Branch");
		
		Department lhp = new Department();
		lhp.setDeptId("00005");
		lhp.setDeptName("Tseung Kwan O - LohasPark Branch");
		
		Department pal2 = new Department();
		pal2.setDeptId("00006");
		pal2.setDeptName("Shatin Res. (Lux.) - Palazzo Branch (2)");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(prh);
			pm.makePersistent(epc5);
			pm.makePersistent(wks);
			pm.makePersistent(lhp);
			pm.makePersistent(pal2);
		} finally {
			pm.close();
		}
		//End of init Department
		
		//Init user
		
		User u1 = new User();
		u1.setUserName("autscr0001");
		u1.setPassword("123456");
		
		User u2 = new User();
		u2.setUserName("autscr0002");
		u2.setPassword("123456");
		
		User u3 = new User();
		u3.setUserName("autscr0003");
		u3.setPassword("123456");
		
		User u4 = new User();
		u4.setUserName("autscr0004");
		u4.setPassword("123456");
		
		User u5 = new User();
		u5.setUserName("autscr0005");
		u5.setPassword("123456");
		
		User u6 = new User();
		u6.setUserName("autscr0006");
		u6.setPassword("123456");
		
		//Init end of user
		
		//Init employee
		
		Employee emp1 =  new Employee();
		emp1.setEmpId("H0000001");
		emp1.setEngSurname("Lam");
		emp1.setEngOtherName("CHONG CHI");
		emp1.setChiSurname("林");
		emp1.setChiOthername("莊姿");
		emp1.setTitle(Title.COOFF);
		emp1.setNickName("JANE");
		emp1.setEmail("autscr0001@gmail.com");
		emp1.setSuperLevel(200);
		
		Employee emp2 =  new Employee();
		emp2.setEmpId("H0000002");
		emp2.setEngSurname("FUNG");
		emp2.setEngOtherName("LIK SHAN");
		emp2.setChiSurname("馮");
		emp2.setChiOthername("力山");
		emp2.setTitle(Title.SD);
		emp2.setNickName("ALEX");
		emp2.setEmail("autscr0002@gmail.com");
		emp2.setSuperLevel(400);
		
		Employee emp3 =  new Employee();
		emp3.setEmpId("H0000003");
		emp3.setEngSurname("PANG");
		emp3.setEngOtherName("YUI MING");
		emp3.setChiSurname("趁");
		emp3.setChiOthername("耀明");
		emp3.setTitle(Title.DM);
		emp3.setNickName("FRANCO");
		emp3.setEmail("autscr0003@gmail.com");
		emp3.setSuperLevel(600);
		
		Employee emp4 =  new Employee();
		emp4.setEmpId("H0000004");
		emp4.setEngSurname("TANG");
		emp4.setEngOtherName("TZE MAN");
		emp4.setChiSurname("鄧");
		emp4.setChiOthername("子敏");
		emp4.setTitle(Title.COOFF);
		emp4.setNickName("RACHEL");
		emp4.setEmail("autscr0004@gmail.com");
		emp4.setSuperLevel(200);
		
		Employee emp5 =  new Employee();
		emp5.setEmpId("H0000005");
		emp5.setEngSurname("YIP");
		emp5.setEngOtherName("OI HA");
		emp5.setChiSurname("葉");
		emp5.setChiOthername("愛霞");
		emp5.setTitle(Title.COOFF);
		emp5.setNickName("CARRIE");
		emp5.setEmail("autscr0005@gmail.com");
		emp5.setSuperLevel(400);
		
		Employee emp6 =  new Employee();
		emp6.setEmpId("H0000006");
		emp6.setEngSurname("LI");
		emp6.setEngOtherName("MAN");
		emp6.setChiSurname("李");
		emp6.setChiOthername("文");
		emp6.setTitle(Title.DM);
		emp6.setNickName("HELEN");
		emp6.setEmail("autscr0006@gmail.com");
		emp6.setSuperLevel(600);
		
		emp1.setDepartment(prh.getDeptKey());
		emp2.setDepartment(prh.getDeptKey());
		emp3.setDepartment(prh.getDeptKey());
		emp4.setDepartment(epc5.getDeptKey());
		emp5.setDepartment(epc5.getDeptKey());
		emp6.setDepartment(epc5.getDeptKey());
		
		u1.setEmployee(emp1);
		u2.setEmployee(emp2);
		u3.setEmployee(emp3);
		u4.setEmployee(emp4);
		u5.setEmployee(emp5);
		u6.setEmployee(emp6);
		//end of init employee
		
		//store emp
		
		pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(u1);
			pm.makePersistent(u2);
			pm.makePersistent(u3);
			pm.makePersistent(u4);
			pm.makePersistent(u5);
			pm.makePersistent(u6);
		} finally {
			pm.close();
		}

		SearchEmployeeUtil.updateIndex(emp1);
		SearchEmployeeUtil.updateIndex(emp2);
		SearchEmployeeUtil.updateIndex(emp3);
		SearchEmployeeUtil.updateIndex(emp4);
		SearchEmployeeUtil.updateIndex(emp5);
		SearchEmployeeUtil.updateIndex(emp6);
		//end of store
	}
}
