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

import edu.fyp.manager.FormManager;

public class UpdateFormStatus extends HttpServlet {
	
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
		String formID = req.getParameter("formID");
		String version = req.getParameter("version");
		String status = req.getParameter("status");
		if (formID.equalsIgnoreCase("") || version.equalsIgnoreCase("")
				|| status.equalsIgnoreCase("")) {
			return ;
		}
		try {
			formManager.updateFormStatus(formID, version, status);
		} catch (Exception e) {
			out.println("Error. Please contact IT support.");
		}
		req.getRequestDispatcher("/formbuilder/showBuilderFormListServlet").forward(req, resp);
	}
}
