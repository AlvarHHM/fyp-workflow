package edu.fyp.repository;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.NoticeNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;

@Repository
public class PathNodeRepository {
	public static void addPathNode(PathNode pathNode){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(pathNode);
		} finally {
			pm.close();
		}
	}
	public static PathNode getNode(Key key){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		PathNode pathNode=null;
	    try {
	    	String classKind = key.getKind();
	    	pathNode = (PathNode) pm.getObjectById(Class.forName("edu.fyp.bean.node."+classKind), key);
	    }catch(Exception e){
	    	
	    } finally {
	        pm.close();
	    } 
	    return pathNode;
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
