package edu.fyp.bean.PathNode;
import java.util.ArrayList;

public class CompositeNode extends RelayNode{
	private ArrayList<PathNode> childNodes;
        private PathNode nextNode;
	public PathNode getChild(String nodeID){
            return null;
	}
        public PathNode getNextNode(){
            return nextNode;
        }
}