package edu.fyp.repository;

import javax.jdo.PersistenceManager;

import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.PathNode;

public class PathNodeRepository {
	public static void test(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PathNode pn = new ApproveNode();
		try {
			System.out.println(pm.makePersistent(pn).getNodeID().toString());
			System.out.println(pn.getNodeID().toString());
		} finally {
			pm.close();
		}
	}
	public static void addPathNode(PathNode pathNode){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			System.out.println(pathNode.getNodeID().toString());
		} finally {
			pm.close();
		}
	}
}
