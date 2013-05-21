package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.FormManager;

public class ReassignApprover extends HttpServlet {

	@Autowired
	private ApplicationManager appManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String appKey = req.getParameter("appKey");
		String nodeKey = req.getParameter("nodeKey");
		String empID = req.getParameter("empID");
		boolean approve;
		if (appKey == null || appKey.equalsIgnoreCase("") || nodeKey == null
				|| nodeKey.equalsIgnoreCase("") || empID == null
				|| empID.equalsIgnoreCase("")) {
			out.println("Application Key, Node Key, employee ID can not be empty.");
			return;
		}

		try {
			appManager.reassignApprover(appKey, nodeKey, empID);
			out.println("Reassigned.");
		} catch (Exception e) {
			Logger.getAnonymousLogger().warning(e.toString());
			out.println("Error. Please contact IT support.");
		}

		out.close();
	}
}
