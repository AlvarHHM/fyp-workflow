package edu.fyp.bean.PathNode;

public class ApproveNode extends RelayNode{
    private PathNode nextTrueNode;
    private PathNode nextFalseNode;
    private PathNode nextNode;
    private String empID;
    private String deptID;
    private int superLevel;
    public String getEmpID(){
        return empID;
    }
    public void setEmpID(String empID){
        this.empID=empID;
    }
    public String getDeptID(){
        return deptID;
    }
    public void setDeptID(String deptID){
        this.deptID=deptID;
    }
    public int getSuperLevel(){
        return superLevel;
    }
    public void setSuperLevel(int superLevel){
        this.superLevel=superLevel;
    }
    public PathNode getNextTrueNode(){
        return nextTrueNode;
    }
    public PathNode getNextFalseNode(){
        return nextFalseNode;
    }
}