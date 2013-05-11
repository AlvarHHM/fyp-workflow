package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class ApproveNode extends RelayNode{
	@Persistent
    private Key nextTrueNode;
	@Persistent
    private Key nextFalseNode;
	@Persistent
    private String empID;
	@Persistent
    private String deptID;
	@Persistent
    private int superLevel;
	public ApproveNode(){
		this.setState("Approving");
	}
	
	public void approve(boolean approve) {
		this.setState("finish");
		if(approve){
			this.setNextNode(this.nextTrueNode);
		}else{
			this.setNextNode(this.nextFalseNode);
		}
	}
	
	public Key getNextTrueNode() {
		return nextTrueNode;
	}
	public void setNextTrueNode(Key nextTrueNode) {
		this.nextTrueNode = nextTrueNode;
	}
	public Key getNextFalseNode() {
		return nextFalseNode;
	}
	public void setNextFalseNode(Key nextFalseNode) {
		this.nextFalseNode = nextFalseNode;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public int getSuperLevel() {
		return superLevel;
	}
	public void setSuperLevel(int superLevel) {
		this.superLevel = superLevel;
	}
  
}