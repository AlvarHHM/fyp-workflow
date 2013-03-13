package edu.fyp.bean.pathNode;

public class NoticeNode extends RelayNode{
    private String empID;
    private String deptID;
    private int superLevel;
    @Override
    public void process(){
        
    }
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
}