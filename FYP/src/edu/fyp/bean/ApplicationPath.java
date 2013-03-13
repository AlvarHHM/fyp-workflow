package edu.fyp.bean;

import javax.jdo.annotations.PersistenceCapable;

import edu.fyp.bean.node.PathNode;
@PersistenceCapable
public class ApplicationPath {

    private PathNode startNode;
    private PathNode currentNode;

    public ApplicationPath() {
    	
    }

    public PathNode getStartNode() {
        return startNode;
    }

    public void setStartNode(PathNode startNode) {
        this.startNode = startNode;
    }

    public PathNode getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(PathNode currentNode) {
        this.currentNode = currentNode;
    }
}