package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Application;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;

public class ApplyApplication extends HttpServlet {
	
	@Autowired
	private ApplicationManager appManager;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String formID = req.getParameter("FormID");
		String version = req.getParameter("Version");
		Text data = new Text(req.getParameter("Data"));
		Application app = new Application();
		app.setEmpID("H0000001");//hardcode
		app.setFormID(formID);
		app.setVersion(version);
		app.setFormData(data);
		app.setApplyDate(new Date());
/*		try {*/
			appManager.applyApplication(app);
			out.println("Application applied.");
/*		} catch (Exception e) {
			System.out.println(e.toString());
			Logger.getAnonymousLogger().warning(e.toString());
			out.println("Error. Please contact IT support.");
		}*/
	}
}