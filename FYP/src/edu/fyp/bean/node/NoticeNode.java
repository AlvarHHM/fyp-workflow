package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class NoticeNode extends RelayNode{
	@Persistent
    private String empID;
	@Persistent
    private String email;
    public void process(){
        System.out.println("Test NoticeNode process");
        this.setState("finish");
    }
    public String getEmpID(){
        return empID;
    }
    public void setEmpID(String empID){
        this.empID=empID;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}