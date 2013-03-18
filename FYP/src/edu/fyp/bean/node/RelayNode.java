package edu.fyp.bean.node;

public class RelayNode extends PathNode{
    private PathNode nextNode;
    public PathNode getNextNode(){
        return null;
    }
    public void setNextNode(PathNode nextNode){
        this.nextNode=nextNode;
    }
	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		
	}
}
