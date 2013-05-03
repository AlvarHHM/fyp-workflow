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

public class ApplyApplication extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		String formID = req.getParameter("formID");
		String version=req.getParameter("version");
		Text data=new Text(req.getParameter("data"));
		Application app = new Application();
		app.setFormID(formID);
		app.setVersion(version);
		app.setFormData(data);
		try{
		ApplicationManager.applyApplication(app);
		out.println("Application applied.");
		}catch(Exception e){
			out.println("Error. Please contact IT support.");
		}
		}
}