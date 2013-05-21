package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

import edu.fyp.bean.Application;
import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.NoticeNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;
import edu.fyp.factory.PathNodeFactory;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.FormManager;
import edu.fyp.repository.PathNodeRepository;

public class ApproveAppNode extends HttpServlet {
	
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
		String approveStr = req.getParameter("approve");
		boolean approve;
		if(approveStr.equalsIgnoreCase("true")){
			approve = true;
		}else if(approveStr.equalsIgnoreCase("false")){
			approve = false;
		}else{
			out.println("Error. Approve only accept true or false.");
			return ;
		}
		
		if (
				appKey.equalsIgnoreCase("") 
				|| appKey == null
				|| nodeKey.equalsIgnoreCase("")
				|| nodeKey == null
				) {
			out.println("Application Key, Node Key, approve can not be empty.");
			return ;
		}
		Application app= appManager.getApplication(KeyFactory.stringToKey(appKey));
		if(app.getStatus().equalsIgnoreCase("Cancelled")){
			out.println("The application is cancelled.");
		}
		try {
			appManager.approveApplicationNode(appKey,nodeKey,approve);
			out.println("Application is processed.");
		} catch (Exception e) {
			Logger.getAnonymousLogger().warning(e.toString());
			out.println("Error. Please contact IT support.");
		}
		
		out.close();
	}
}
