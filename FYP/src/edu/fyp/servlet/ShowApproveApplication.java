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
import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class ShowApproveApplication extends HttpServlet {
	
	@Autowired
	private FormManager formManager;
	@Autowired
	private ApplicationRepository appRepo;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Form form = null;
		Application app = null;
		String formID = req.getParameter("formID");
		String version=req.getParameter("version");
		String appKeyStr= req.getParameter("appKey");
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		form = formManager.getFormByIDVersion(formID, version);
		app = appRepo.getApplication(appKey);
		req.getSession().setAttribute("form", form);
		req.getSession().setAttribute("app", app);
		req.getRequestDispatcher("/Client/showApproveApplication.jsp").forward(req, resp);
	}
}
