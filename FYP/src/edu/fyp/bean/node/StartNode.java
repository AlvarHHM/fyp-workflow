package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class StartNode extends RelayNode {
	public StartNode(Key nextNode) {
		super.setState("finish");
		super.setNextNode(nextNode);
	}
}
