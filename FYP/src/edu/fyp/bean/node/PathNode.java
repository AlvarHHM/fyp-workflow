package edu.fyp.bean.node;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.InheritanceStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class PathNode {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key nodeKey;
	@Persistent
	private String state;
	@Persistent
	private String nodeID;
	
	public abstract void process();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNodeID() {
		return nodeID;
	}

	public void setNodeID(String nodeID) {
		this.nodeID = nodeID;
	}

	public Key getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(Key nodeKey) {
		this.nodeKey = nodeKey;
	}
}