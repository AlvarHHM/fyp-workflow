package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class NoticeNode extends RelayNode{
	@Persistent
    private String empID;
	@Persistent
    private String deptID;
	@Persistent
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