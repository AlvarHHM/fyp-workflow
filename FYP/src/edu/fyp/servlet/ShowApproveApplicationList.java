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

import edu.fyp.bean.Application;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;
import edu.fyp.repository.UserRepository;

public class ShowApproveApplicationList extends HttpServlet {
	
	@Autowired
	private ApplicationRepository appRepo;

	@Autowired
	private FormRepository formRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String empID = "H0000002";//hard code
		String search = req.getParameter("search");
		String keyword = req.getParameter("keyword");
		List<Application> appList = null;
		if(search !=null && keyword!=null & !keyword.equalsIgnoreCase("")){
			appList = appRepo.searchApproveApplication(search, keyword, empID);
		}else{
			appList = appRepo.getApproveApplication(empID);
		}
		ArrayList<Form> formList = formRepo.getAllForm();
		ArrayList<Employee> empList = new ArrayList<Employee>();
		for(int i = 0 ; i < appList.size() ; i ++){
			
		}
		req.getSession().setAttribute("formList", formList);
		req.getSession().setAttribute("appList", appList);
		req.getRequestDispatcher("/Client/showApproveApplicationList").forward(req, resp);
	}
}
