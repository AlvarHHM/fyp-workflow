package edu.fyp.bean;

import javax.jdo.annotations.IdGeneratorStrategy;


import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ApplicationPath {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
	@Persistent
    private Key startNode;
	@Persistent
    private Key currentNode;

	
    public ApplicationPath() {
    	
    }

    public ApplicationPath(Key startNode, Key currentNode) {
    	this.startNode = startNode;
    	this.currentNode = currentNode;
    }

    public Key getStartNode() {
        return startNode;
    }

    public void setStartNode(Key startNode) {
        this.startNode = startNode;
    }

    public Key getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Key currentNode) {
        this.currentNode = currentNode;
    }
    
    public Key getKey() {
        return key;
    } 

    public void setKey(Key key) {
        this.key = key;
    }
}