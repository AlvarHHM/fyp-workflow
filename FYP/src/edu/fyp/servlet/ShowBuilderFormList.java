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

import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.bean.User;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class ShowBuilderFormList extends HttpServlet {
	
	@Autowired
	private FormManager formManager;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String keyword = req.getParameter("keyword");
		ArrayList<Form> formList = null;
		if(keyword!=null && !keyword.equalsIgnoreCase("")){
			formList = formManager.searchForm(keyword);
		}else{
			formList = formManager.getAllForm();
		}
		req.getSession().setAttribute("formList", formList);
		req.getRequestDispatcher("/formbuilder/showBuilderFormList").forward(req, resp);
	}
}
