package edu.fyp.bean.PathNode;

public class StartNode  extends RelayNode{
    public StartNode(PathNode nextNode){
        super.setState("finish");
        super.setNextNode(nextNode);
    }
}
