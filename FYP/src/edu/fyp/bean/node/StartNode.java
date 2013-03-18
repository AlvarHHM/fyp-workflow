package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class StartNode  extends RelayNode{
    public StartNode(PathNode nextNode){
        super.setState("finish");
        super.setNextNode(nextNode);
    }
}
