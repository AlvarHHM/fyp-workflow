package edu.fyp.repository;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;

public class ApplicationPathRepository {
	public static void addApplicationPath(ApplicationPath appPath){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(appPath);
			System.out.println(appPath.getKey()+" added");
		} finally {
			pm.close();
		}
	}
	
	public static void updateStartNode(Key pathKey,Key nodeKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath;
		try {
			appPath = pm.getObjectById(ApplicationPath.class, pathKey);
			appPath.setStartNode(nodeKey);
		} finally {
			pm.close();
		}
	}
	
	public static void updateCurrentNode(Key pathKey,Key nodeKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath;
		try {
			appPath = pm.getObjectById(ApplicationPath.class, pathKey);
			appPath.setCurrentNode(nodeKey);
		} finally {
			pm.close();
		}
	}
	
	public static ApplicationPath getApplication(Key key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath=null;
		try {
			appPath = pm.getObjectById(ApplicationPath.class, key);
		}catch(Exception e){
			System.out.println(e.toString());
		}finally {
			pm.close();
		}
		return appPath;
	}
}
