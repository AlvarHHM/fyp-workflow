package edu.fyp.repository;

import javax.jdo.PersistenceManager;

import edu.fyp.bean.ApplicationPath;

public class ApplicationPathRepository {
	public static void addApplicationPath(ApplicationPath appPath){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(appPath);
		} finally {
			pm.close();
		}
	}
}
