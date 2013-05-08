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

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class ShowClientApplication extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		//Main
/*		Form form = null;
		Application app = null;
		String formID = req.getParameter("formID");
		String version=req.getParameter("version");
		String appKeyStr= req.getParameter("appKey");
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		form = FormRepository.getFormByIDVersion(formID, version);
		app = ApplicationRepository.getApplication(appKey);
		req.getSession().setAttribute("form", form);
		req.getSession().setAttribute("app", app);
		req.getRequestDispatcher("/Client/showClientApplication").forward(req, resp);*/
		
		
		//only for test
		String appKeyStr= req.getParameter("appKey");
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		Application app = ApplicationRepository.getApplication(appKey);
		ApplicationPath appPath = app.getAppPath();
		System.out.println(KeyFactory.keyToString(appPath.getKey()));
		System.out.println(KeyFactory.keyToString(appPath.getCurrentNode()));
	}
}
