package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.fyp.bean.Application;
import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class ShowClientApplicationList extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String empID = "A";//hard code
		String search = req.getParameter("search");
		String keyword = req.getParameter("keyword");
		ArrayList<Application> appList = null;
/*		if(search !=null && keyword!=null & !keyword.equalsIgnoreCase("")){
			formList = FormManager.searchForm( search, keyword);
		}else{
			formList = FormManager.getAllForm();
		}*/
		appList = ApplicationRepository.getAllApplication();
		req.getSession().setAttribute("appList", appList);
		req.getRequestDispatcher("/Client/showClientApplicationList").forward(req, resp);
	}
}
