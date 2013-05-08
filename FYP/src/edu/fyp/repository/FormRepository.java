package edu.fyp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Form;

@Repository
public class FormRepository {
	public void addForm(Form form) {
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
	public Form getFormByIDVersion(String formID,String version) {
		Form form = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = generateKey(formID,version);
		form = pm.getObjectById(Form.class, k);
		pm.close();
		return form;
	}
	public void updateFormPath(String formID,String version, Text path) {
		Form form = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = generateKey(formID,version);
		form = pm.getObjectById(Form.class, k);
		form.setPath(path);
		pm.close();
	}
	public ArrayList<Form> getAllForm() {
		List<Form> formList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Form.class);
		q.setOrdering("formID asc");
		formList = (List<Form>) q.execute();
		pm.close();
		return listToArrayListForm(formList);
	}
	public ArrayList<Form> searchForm(String search,String keyword) {
		List<Form> formList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Form.class);
		q.setFilter(search + " == keyword");
		q.setOrdering("formID asc");
		q.declareParameters("String keyword");
		formList = (List<Form>) q.execute(keyword);
		pm.close();
		return listToArrayListForm(formList);
	}
	protected ArrayList<Form> listToArrayListForm(List<Form> formList){
		ArrayList<Form> formArrayList = new ArrayList<Form>();
		for(int i=0;i<formList.size();i++){
			formArrayList.add((Form)formList.get(i));
		}
		return formArrayList;
	}
	public Key generateKey(String formID,String version){
		Key k =KeyFactory.createKey(Form.class.getSimpleName(),
				"FormID:"+formID+"+Version:"+version);
		return k;
	}
}
