package edu.fyp.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;

public class FormBuilderAddFormTest extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Form form = new Form();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if (req.getParameter("action").equalsIgnoreCase("0")) {
			for (int i = 0; i < 10; i++) {
				form.setFormHtml("html"+i);
				form.setFormID("formID"+i);
				form.setVersion("version"+i);
				FormRepository.addForm(form);
			}
		} else {
			form = FormRepository.getFormByIDVersion("A1", "2");
			System.out.println(form.getFormHtml());
		}
		pm.close();
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Date createdDate = Calendar.getInstance().getTime();

		String formID=req.getParameter("formID");
		String formHtml=req.getParameter("formHtml");
		String description=req.getParameter("description");
		String title=req.getParameter("title");
		String version=req.getParameter("version");
		String constraint=req.getParameter("constraint");
		//String createdBy= Session
		
		Form form = new Form();
		form.setFormID(formID);
		form.setFormHtml(formHtml);
		form.setDescription(description);
		form.setTitle(title);
		form.setVersion(version);
		form.setConstraint(constraint);
		FormRepository.addForm(form);
	}
}