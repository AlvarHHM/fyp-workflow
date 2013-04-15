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
	private Key nodeID;
	@Persistent
	private String state;

	public abstract void process();

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Key getNodeID() {
		return nodeID;
	}

	public void setNodeID(Key nodeID) {
		this.nodeID = nodeID;
	}
}