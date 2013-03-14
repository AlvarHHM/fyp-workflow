package edu.fyp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Form;

public class FormRepository {
	public static void addForm(Form form) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k =	generateKey(form.getFormID(), form.getVersion());
		form.setKey(k);
		try {
			pm.makePersistent(form);
		} finally {
			pm.close();
		}
	}
	//get the form by ID and version
	public static Form getFormByIDVersion(String formID,String version) {
		Form form = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = generateKey(formID,version);
		form = pm.getObjectById(Form.class, k);
		return form;
	}

	public static List<Form> getAllFormByEmpID(String empID) {
		return null;
	}
	public static Key generateKey(String formID,String version){
		Key k =KeyFactory.createKey(Form.class.getSimpleName(),
				"FormID:"+formID+"+Version:"+version);
		return k;
	}
}
