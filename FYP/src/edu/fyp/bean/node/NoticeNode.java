package edu.fyp.bean.node;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;

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
	
	@Autowired
	ApplicationManager appManager;
	
	
	public void process(){
		Application app= appManager.getApplicationByCurrentNode(this.getNodeKey());
		MailNotice mn = new MailNotice();
		MailBody mb;
		try {
			mb = new MailBody("WEB-INF/mail-template/NotifyOfNotice.html");
			mb.setProperty("%applyDate", app.getApplyDate().toString());
			mb.setProperty("%message", this.getNoticeMessage());
			mn.setTitle("Application Notice - ");
			mn.setTo(this.getEmail());
			Logger.getAnonymousLogger().warning(this.getEmail());
			NoticeMailService.getIntance().batchNotice(mn);
			NoticeMailService.getIntance().processBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
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