package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import edu.fyp.bean.Application;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;
import edu.fyp.repository.UserRepository;

public class Home extends HttpServlet {
	
	@Autowired
	private FormManager formManager;

	@Autowired
	private ApplicationRepository appRepo;

	@Autowired
	private UserRepository userRepo;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Employee emp = (Employee) req.getSession().getAttribute("EMP");
		String empID = emp.getEmpId();
		ArrayList<Form> formList = formManager.getAllReleaseForm();
		ArrayList<Application> appList = (ArrayList<Application>) appRepo.getEmpApplication(empID);
		ArrayList<Application> approveAppList = (ArrayList<Application>) appRepo.getApproveApplication(empID);
		ArrayList<Employee> applierList = new ArrayList<Employee>();
		ArrayList<Form> approveFormList = new ArrayList<Form>();
		ArrayList<Form> appFormList = new ArrayList<Form>();
		for(int i=0;i < approveAppList.size() ; i++){
			applierList.add(userRepo.queryEmployeeByEmpID(approveAppList.get(i).getApprovingEmpID()));
			approveFormList.add(formManager.getFormByIDVersion(approveAppList.get(i).getFormID(),
					approveAppList.get(i).getVersion()));
		}
		for(int i=0;i < appList.size() ; i++){
			appFormList.add(formManager.getFormByIDVersion(appList.get(i).getFormID(),
					appList.get(i).getVersion()));
		}
		Logger.getAnonymousLogger().warning("form list:"+formList.size());
		Logger.getAnonymousLogger().warning("app list:"+appList.size());
		Logger.getAnonymousLogger().warning("approveApp list:"+approveAppList.size());
		Logger.getAnonymousLogger().warning("applier list:"+applierList.size());
		Logger.getAnonymousLogger().warning("approveForm list:"+approveFormList.size());
		Logger.getAnonymousLogger().warning("appForm list:"+appFormList.size());
		req.getSession().setAttribute("formList", formList);
		req.getSession().setAttribute("appList", appList);
		req.getSession().setAttribute("approveAppList", approveAppList);
		req.getSession().setAttribute("applierList", applierList);
		req.getSession().setAttribute("approveFormList", approveFormList);
		req.getSession().setAttribute("appFormList", appFormList);
		req.getRequestDispatcher("/Client/home.jsp").forward(req, resp);
	}
}
