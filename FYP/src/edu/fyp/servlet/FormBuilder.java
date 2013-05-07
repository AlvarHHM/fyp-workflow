package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;

public class FormBuilder extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<form>This is form.</form>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		if(req.getParameter("FormID").equalsIgnoreCase("")||
				req.getParameter("Version").equalsIgnoreCase("")){
			out.print("Form ID and Version can not be empty.");
			out.close();
			return ;
		}
		Form form = new Form();
		form.setFormHtml(new Text(req.getParameter("FormHtml")));
		form.setConstraint(req.getParameter("Constraint"));
		form.setDescription(req.getParameter("Description"));
		form.setTitle(req.getParameter("Title"));
		form.setCreatedDate(new Date());
		form.setCreatedBy("hardCodeOne");
		form.setFormID(req.getParameter("FormID"));
		form.setVersion(req.getParameter("Version"));
		try{
			FormRepository.addForm(form);
		}catch(Exception ex){
			out.println("Error! Form design NOT saved!");
		}
		out.println("Sucess");
		out.close();
	}
}
