package edu.fyp.servlet;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Form;
import edu.fyp.repository.PMF;

public class FormBuilderAddFormTest extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Form form = new Form();
		form.setForm("Hello");
		form.setFormID("A1");
		form.setVersion("1");
		Key formKey = KeyFactory.createKey(Form.class.getSimpleName(),"FormID:"+form.getFormID()+"Version:"+form.getVersion());
		form.setKey(formKey);
		System.out.println(form.getKey().toString());
		PersistenceManager pm =PMF.get().getPersistenceManager();
		try{
			pm.makePersistent(form);
		}finally{
		
		}
		form=null;
		   Key k = KeyFactory.createKey(Form.class.getSimpleName(),"FormID:A1Version:1");
	       form = pm.getObjectById(Form.class, k);
		System.out.println(form.getForm());
		pm.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String constraint=req.getParameter("a");
		String createdBy=req.getParameter("a");
		String createdDate=req.getParameter("a");
		String formHtml=req.getParameter("a");
		String formID=req.getParameter("a");
		String version=req.getParameter("a");
		String constraint=req.getParameter("a");
		Form form = new Form();
		form.setConstraint(constraint);
		form.setCreatedBy(createdBy);
		form.setCreatedDate(createdDate);
		form.setForm(formHtml);
		form.setFormID(formID);
		form.setKey(key);
		form.setVersion(version);
	}
}