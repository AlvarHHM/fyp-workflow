package edu.fyp.bean.pathNode;

public class StartNode  extends RelayNode{
    public StartNode(PathNode nextNode){
        super.setState("finish");
        super.setNextNode(nextNode);
    }
}
