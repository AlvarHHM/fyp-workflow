package edu.fyp.bean.node;

public class StartNode  extends RelayNode{
    public StartNode(PathNode nextNode){
        super.setState("finish");
        super.setNextNode(nextNode);
    }
}
