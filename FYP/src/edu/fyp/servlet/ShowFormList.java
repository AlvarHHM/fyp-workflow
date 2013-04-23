package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
		String testStr = "Power powerful";
		req.getSession().setAttribute("testStr", testStr);
		req.getRequestDispatcher("/Client/showFormList").forward(req, resp);
	}
}
