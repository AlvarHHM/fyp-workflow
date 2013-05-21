package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.FormManager;

public class UpdateAdminApplicationStatus extends HttpServlet {
	
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
		String appKeyStr = req.getParameter("appKey");
		String status = req.getParameter("status");
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		if (appKeyStr.equalsIgnoreCase("")) {
			out.println("Application ID can not be empty.");
			return ;
		}
		try {
			appManager.updateApplicationStatus(appKey, status);
			out.println("Application status is changed.");
		} catch (Exception e) {
			out.println("Error. Please contact IT support.");
		}
		req.getRequestDispatcher("/Admin/showAdminApplicationListServlet").forward(req, resp);
	}
}
