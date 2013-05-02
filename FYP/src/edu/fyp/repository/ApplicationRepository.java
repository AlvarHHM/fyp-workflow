package edu.fyp.repository;


import java.util.ArrayList;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Form;

public class ApplicationRepository{
	public static void addApplication(Application app){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(app);
		} finally {
			pm.close();
		}
	}
	public static void updateApplicationPath(Key key, ApplicationPath appPath){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Application app = pm.getObjectById(Application.class, key);
			app.setApplicationPath(appPath);
		} finally {
			pm.close();
		}
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
	
}