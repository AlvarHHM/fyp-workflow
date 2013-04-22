package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;

public class showFromList extends HttpServlet {
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
		Form form = new Form();
		form.setFormHtml(req.getParameter("FormHtml"));
		form.setConstraint(req.getParameter("Constraint"));
		form.setCreatedDate(new Date());
		form.setCreatedBy("hardCodeOne");
		form.setFormID("hardCodeFormID");
		form.setVersion("hardCodeVersion");
		form.setFormID("AAA");
		form.setVersion("AAA");
		FormRepository.addForm(form);
	}

	protected void processRequest(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
        Enumeration names = req.getParameterNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            out.println(name + ": " + req.getHeader(name) + "<br>");
        }
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
}
