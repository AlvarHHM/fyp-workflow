package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Application;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.repository.FormRepository;

public class ApplyApplication extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String formID = req.getParameter("FormID");
		String version = req.getParameter("Version");
		Text data = new Text(req.getParameter("Data"));
		System.out.println(formID);
		System.out.println(version);
		System.out.println(data.getValue());
		Application app = new Application();
		app.setFormID(formID);
		System.out.println(app.getFormID());
		System.out.println(app.getVersion());
/*		System.out.println(app.getFormData().getValue());*/
		/*try {*/
			ApplicationManager.applyApplication(app);
			out.println("Application applied.");
/*		} catch (Exception e) {
			System.out.println(e.toString());
			out.println("Error. Please contact IT support.");
		}*/
	}
}