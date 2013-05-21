package edu.fyp.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Application;
import edu.fyp.bean.Form;
import edu.fyp.search.SearchUtil;

@Repository
public class FormRepository {
	public void addForm(Form form) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k =	generateKey(form.getFormID(), form.getVersion());
		form.setKey(k);
		SearchUtil.updateFormIndex(form);
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
	
	public Form getFormByFormKey(Key formKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		return pm.getObjectById(Form.class,formKey);
		
		
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
		q.setOrdering("createdDate desc");
		formList = (List<Form>) q.execute();
		pm.close();
		return listToArrayListForm(formList);
	}
	public ArrayList<Form> searchForm(String keyword) {
		List<Form> formList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
//		Query q = pm.newQuery(Form.class);
//		q.setFilter(search + " == keyword");
//		q.declareParameters("String keyword");
//		q.setOrdering("createdDate desc");
//		formList = (List<Form>) q.execute(keyword);
//		pm.close();
		
		String queryString = keyword.toUpperCase();
		StringBuffer queryBuffer = new StringBuffer();

		queryBuffer.append("SELECT FROM " + Form.class.getName()
				+ " WHERE ");

		StringBuffer declareParametersBuffer = new StringBuffer();

		Set<String> queryTokens = new HashSet<String>();
		for (String token : queryString.split(" ")) {
			queryTokens.add(token.trim().toUpperCase());
		}
		List<String> parametersForSearch = new ArrayList<String>(queryTokens);
		int parameterCounter = 0;

		while (parameterCounter < queryTokens.size()) {

			queryBuffer.append("fts == param" + parameterCounter);
			declareParametersBuffer.append("String param" + parameterCounter);

			if (parameterCounter + 1 < queryTokens.size()) {
				queryBuffer.append(" && ");
				declareParametersBuffer.append(", ");

			}

			parameterCounter++;

		}

		Query query = pm.newQuery(queryBuffer.toString());

		query.declareParameters(declareParametersBuffer.toString());
		
		formList = (List<Form>) query
				.executeWithArray(parametersForSearch.toArray());
		
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
	public void updateFormStatus(String formID, String version, String status) {
		Form form = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key k = generateKey(formID,version);
		form = pm.getObjectById(Form.class, k);
		form.setStatus(status);
		pm.close();
	}
	public void updateFormPath(String formKey, Text path) {
		Form form = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		form = pm.getObjectById(Form.class, formKey);
		form.setPath(path);
		pm.close();
	}
	public ArrayList<Form> searchReleaseForm(String keyword) {
		List<Form> formList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
//		Query q = pm.newQuery(Form.class);
//		q.setFilter(search + " == keyword && status == Release");
//		q.declareParameters("String keyword");
//		q.setOrdering("createdDate desc");
//		formList = (List<Form>) q.execute(keyword);
//		pm.close();
		
		String queryString = keyword.toUpperCase();
		StringBuffer queryBuffer = new StringBuffer();

		queryBuffer.append("SELECT FROM " + Form.class.getName()
				+ " WHERE status == 'Release' && ");

		StringBuffer declareParametersBuffer = new StringBuffer();

		Set<String> queryTokens = new HashSet<String>();
		for (String token : queryString.split(" ")) {
			queryTokens.add(token.trim().toUpperCase());
		}
		List<String> parametersForSearch = new ArrayList<String>(queryTokens);
		int parameterCounter = 0;

		while (parameterCounter < queryTokens.size()) {

			queryBuffer.append("fts == param" + parameterCounter);
			declareParametersBuffer.append("String param" + parameterCounter);

			if (parameterCounter + 1 < queryTokens.size()) {
				queryBuffer.append(" && ");
				declareParametersBuffer.append(", ");

			}

			parameterCounter++;

		}

		Query query = pm.newQuery(queryBuffer.toString());

		query.declareParameters(declareParametersBuffer.toString());
		
		formList = (List<Form>) query
				.executeWithArray(parametersForSearch.toArray());
		
		return listToArrayListForm(formList);
	}
	
	public ArrayList<Form> getAllReleaseForm() {
		List<Form> formList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Form.class);
		q.setFilter("status == 'Release'");
		q.setOrdering("createdDate desc");
		formList = (List<Form>) q.execute();
		pm.close();
		return listToArrayListForm(formList);
	}
}
