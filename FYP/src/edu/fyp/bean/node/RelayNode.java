package edu.fyp.bean.node;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.InheritanceStrategy;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class RelayNode extends PathNode {
	@Persistent
	private Key nextNode;

	public Key getNextNode() {
		return nextNode;
	}

	public void setNextNode(Key nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub

	}

}
