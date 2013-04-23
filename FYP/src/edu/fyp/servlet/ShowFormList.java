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

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class ShowFormList extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String empID = "A";//hard code
		String search = req.getParameter("seacrh");
		String keyword = req.getParameter("keyword");
		ArrayList<Form> formList = null;
		if(search !=null && keyword!=null){
			//formList = searchForm(search,keyword);
			out.println("not impl");
		}else{
			formList = FormRepository.getAllFormByEmpID(empID);
		}
		req.getSession().setAttribute("formList", formList);
		req.getRequestDispatcher("/Client/showFormList").forward(req, resp);
	}
}
