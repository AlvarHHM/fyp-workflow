package edu.fyp.bean.node;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.InheritanceStrategy;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class RelayNode extends PathNode {
	@Persistent
	private PathNode nextNode;

	public PathNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(PathNode nextNode) {
		this.nextNode = nextNode;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub

	}

}
