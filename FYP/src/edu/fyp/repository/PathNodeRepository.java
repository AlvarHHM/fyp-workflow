package edu.fyp.repository;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.NoticeNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;

public class PathNodeRepository {
	public static void test(){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PathNode pn = new ApproveNode();
		try {
			System.out.println(pm.makePersistent(pn).getNodeID().toString());
			System.out.println(pn.getNodeID().toString()+ "tt");
		} finally {
			pm.close();
		}
	}
	public static void addPathNode(PathNode pathNode){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pathNode);
		} finally {
			pm.close();
		}
	}
	public static PathNode getStartNode(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		StartNode sn=null;
	    try {
	    	sn = pm.getObjectById(StartNode.class, key);
	    } finally {
	        pm.close();
	    } 
	    return sn;
	}
	public static void updateStartNextNode(StartNode startNode, Key nextNodeKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	    	StartNode sn = pm.getObjectById(StartNode.class, startNode.getNodeID());
	        sn.setNextNode(nextNodeKey);
	    } finally {
	        pm.close();
	    }
	}
	public static void updateNoticeNextNode(NoticeNode noticeNode, Key nextNodeKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	    	NoticeNode nn = pm.getObjectById(NoticeNode.class, noticeNode.getNodeID());
	        nn.setNextNode(nextNodeKey);
	    } finally {
	        pm.close();
	    }
	}
	public static void updateApproveNextTrueNode(ApproveNode approveNode, Key nextNodeKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	    	ApproveNode an = pm.getObjectById(ApproveNode.class, approveNode.getNodeID());
	        an.setNextTrueNode(nextNodeKey);
	    } finally {
	        pm.close();
	    }
	}
	public static void updateApproveNextFalseNode(ApproveNode approveNode, Key nextNodeKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	    	ApproveNode an = pm.getObjectById(ApproveNode.class, approveNode.getNodeID());
	        an.setNextFalseNode(nextNodeKey);
	    } finally {
	        pm.close();
	    }
	}
}
