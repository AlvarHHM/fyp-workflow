package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;

public class AddFormPath extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
/*		String formID = req.getParameter("formID");
		String version = req.getParameter("version");
		
		Form form = get
		form.setPath(path);*/
		
		
		//for application generate path node test // not for this class - only test
		Text path =new Text(req.getParameter("path"));
		out.println(path.getValue());
		try {
			JSONObject pathJson = new JSONObject(path.getValue());
			out.println(pathJson.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
