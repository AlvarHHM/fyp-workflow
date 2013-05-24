package edu.fyp.servlet;
import java.io.IOException;
import java.io.PrintWriter;

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

import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class ShowMaintainForm extends HttpServlet{
	
	@Autowired
	private FormManager formManager;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Form form = null;
		String appKeyStr = req.getParameter("appKey");
		form = formManager.getFormByFormKey(appKeyStr);
		req.getSession().setAttribute("form", form);
		req.getRequestDispatcher("/formbuilder/formBuilder.jsp").forward(req, resp);
	}
}
