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
public class ApplicationRepository{
	public static void addApplication(Application app){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(app);
		} finally {
			pm.close();
		}
	}
	public static Application updateApplicationPath(Key key, Key appPath){
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
	public static Application getApplication(Key key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Application app=null;
		try {
			app = pm.getObjectById(Application.class, key);
		} finally {
			pm.close();
		}
		return app;
	}
	public static ArrayList<Application> getAllApplication() {
		List<Application> appList = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Application.class);
		appList = (List<Application>) q.execute();
		pm.close();
		return listToArrayListForm(appList);
	}
	protected static ArrayList<Application> listToArrayListForm(List<Application> appList){
		ArrayList<Application> appArrayList = new ArrayList<Application>();
		for(int i=0;i<appList.size();i++){
			appArrayList.add((Application)appList.get(i));
		}
		return appArrayList;
	}
}