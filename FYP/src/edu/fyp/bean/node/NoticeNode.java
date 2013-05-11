package edu.fyp.bean.node;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class NoticeNode extends RelayNode{
	@Persistent
    private String empID;
	@Persistent
    private String email;
	@Persistent 
	private String noticeMessage="";
	@Persistent 
	private String type;
	
	public void process(){
        System.out.println("Test NoticeNode process");
        System.out.println(type);
        System.out.println(email);
        System.out.println(noticeMessage);
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
    
	public String getNoticeMessage() {
		return noticeMessage;
	}
	
	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}
	
    public String getType() {
		return type;
	}
    
	public void setType(String type) {
		this.type = type;
	}
}