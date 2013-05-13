package edu.fyp.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;

@Repository
public class ApplicationPathRepository {
	public void addApplicationPath(ApplicationPath appPath) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(appPath);
			System.out.println(appPath.getKey() + " added");
		} finally {
			pm.close();
		}
	}

	public void updateStartNode(Key pathKey, Key nodeKey) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath;
		try {
			appPath = pm.getObjectById(ApplicationPath.class, pathKey);
			appPath.setStartNode(nodeKey);
		} finally {
			pm.close();
		}
	}

	public void updateCurrentNode(Key pathKey, Key nodeKey) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath;
		try {
			appPath = pm.getObjectById(ApplicationPath.class, pathKey);
			appPath.setCurrentNode(nodeKey);
		} finally {
			pm.close();
		}
	}

	public ApplicationPath getApplicationPath(Key key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath = null;
		try {
			appPath = pm.getObjectById(ApplicationPath.class, key);
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			pm.close();
		}
		return appPath;
	}

	public ApplicationPath getApplicationPathByCurrentNode(Key nodeKey) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		ApplicationPath appPath = null;
		System.out.println("find - "+nodeKey);

		Query query = pm.newQuery(ApplicationPath.class,
				" currentNode == nodeKey ");
		query.declareParameters("com.google.appengine.api.datastore.Key nodeKey");
		List<ApplicationPath> results = (List<ApplicationPath>) query.execute(nodeKey);
		System.out.println(results.size());
		for(int i = 0 ; i < results.size() ; i++){
			if(results.get(i).getCurrentNode().compareTo(nodeKey)==0){
				appPath = results.get(i);
				break;
			}
		}
		pm.close();
		return appPath;
	}
}
