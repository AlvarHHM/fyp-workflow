package edu.fyp.bean.node;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PathNodeRepository;
import edu.fyp.repository.UserRepository;

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

	@Autowired
	private PathNodeRepository pathNodeRepo;
	
	@Autowired
	private FormRepository formRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public void process(){
		Application app= appManager.getApplicationByCurrentNode(this.getNodeKey());
		Form form =formRepo.getFormByIDVersion(app.getFormID(), app.getVersion());
		Department dept;
		Employee applier;
		applier = userRepo.queryEmployeeByEmpID(app.getEmpID());
		dept = userRepo.queryDepartmentByDeptKey(applier.getDepartment());
		MailNotice mn = new MailNotice();
		MailBody mb;
		try {
			mb = new MailBody("WEB-INF/mail-template/NotifyOfNotice.html");
			mb.setProperty("applyDate", app.getApplyDate().toString());
			mb.setProperty("applier", applier.getEngOtherName()+applier.getEngOtherName());
			mb.setProperty("department", dept.getDeptName());
			mb.setProperty("formTitle", form.getTitle());
			mb.setProperty("message", this.getNoticeMessage());
			mn.setTitle("Application Notice - " + form.getTitle()
					+ " by " + applier.getEngOtherName()+applier.getEngOtherName());
			mn.setTo(this.getEmail());
			mn.setBody(mb);
			Logger.getAnonymousLogger().warning(this.getEmail());
			NoticeMailService.getIntance().batchNotice(mn);
			NoticeMailService.getIntance().processBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().warning(e.toString());
		}
        this.setState("finish");
		pathNodeRepo.updateNodeDate(this);
        pathNodeRepo.updateNodeState(this, this.getState());
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