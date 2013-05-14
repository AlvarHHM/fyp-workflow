package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;
import edu.fyp.repository.UserRepository;

public class ShowApproveApplication extends HttpServlet {
	
	@Autowired
	private FormManager formManager;
	
	@Autowired
	private ApplicationRepository appRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ApplicationPathRepository appPathRepo;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String appKeyStr= req.getParameter("appKey");
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		Application app = appRepo.getApplication(appKey);
		ApplicationPath appPath = appPathRepo.getApplicationPath(app.getAppPath());
		Form form = formManager.getFormByIDVersion(app.getFormID(), app.getVersion());
		Employee emp = userRepo.queryEmployeeByEmpID(app.getEmpID());
		Department dept = userRepo.queryDepartmentByDeptKey(emp.getDepartment());
		req.getSession().setAttribute("emp", emp);
		req.getSession().setAttribute("form", form);
		req.getSession().setAttribute("dept", dept);
		req.getSession().setAttribute("app", app);
		req.getSession().setAttribute("appPath", appPath);
		req.getRequestDispatcher("/Client/showApproveApplication").forward(req, resp);
	}
}
