package edu.fyp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Form;

@Repository
public class ApplicationRepository {
	public void addApplication(Application app) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(app);
		} finally {
			pm.close();
		}
	}

	public Application updateApplicationPath(Key key, Key appPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Application app;
		try {
			app = pm.getObjectById(Application.class, key);
			app.setAppPath(appPath);
		} finally {
			pm.close();
		}
		return app;
	}

	public Application updateApplicationStatus(Key key, String status) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Application app;
		try {
			app = pm.getObjectById(Application.class, key);
			app.setStatus(status);
		} finally {
			pm.close();
		}
		return app;
	}

	public Application getApplication(Key key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Application app = null;
		try {
			app = pm.getObjectById(Application.class, key);
		} finally {
			pm.close();
		}
		return app;
	}

	public ArrayList<Application> getAllApplication() {
		List<Application> appList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Application.class);
		appList = (List<Application>) q.execute();
		pm.close();
		return listToArrayListForm(appList);
	}

	protected ArrayList<Application> listToArrayListForm(
			List<Application> appList) {
		ArrayList<Application> appArrayList = new ArrayList<Application>();
		for (int i = 0; i < appList.size(); i++) {
			appArrayList.add((Application) appList.get(i));
		}
		return appArrayList;
	}

	public Application getApplicationByPathKey(Key pathKey) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Application app = null;
		Query query = pm.newQuery(Application.class,
				" appPath == pathKey ");
		query.declareParameters("String pathKey");
		List<Application> results = (List<Application>) query
				.execute(pathKey);
		for( int i = 0 ; i < results.size() ; i++){
			if(results.get(i).getAppPath().compareTo(pathKey)==0){
				app = results.get(i);
				break;
			}
		}
		pm.close();
		return app;
	}

	public List<Application> searchApplication(String search, String keyword,String empID) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Form.class);
		q.setFilter(search + " == keyword");
		q.declareParameters("String keyword");
		List<Application> results = (List<Application>) q.execute(keyword);
		pm.close();
		return results;
	}

	public List<Application> getEmpApplication(String empID) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Form.class);
		q.setFilter("empID == empIDStr");
		q.declareParameters("String empIDStr");
		List<Application> results = (List<Application>) q.execute(empID);
		pm.close();
		return results;
	}
}