package edu.fyp.bean.node;

import java.util.ArrayList;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class CompositeNode extends RelayNode {
	@Persistent
	private ArrayList<PathNode> childNodes;
//	@Persistent
//	private PathNode nextNode;

	public PathNode getChild(String nodeID) {
		return null;
	}

//	public PathNode getNextNode() {
//		return nextNode;
//	}
}