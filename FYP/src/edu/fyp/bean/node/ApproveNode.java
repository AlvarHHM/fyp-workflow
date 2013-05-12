package edu.fyp.bean.node;

import java.io.IOException;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.datastore.Key;

import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.ApplicationPathGenerator;
import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.PathNodeRepository;

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

	public ApproveNode() throws IOException {
		ApplicationManager appManager = new ApplicationManager();
		MailNotice mn = new MailNotice();
		MailBody mb = new MailBody("/mail-template/NotifyOfComingAproval.html");
		mb.setProperty("userId", "User ID");
		mn.setTitle("test");
		mn.setTo("mahoihei@gmial.com");
		NoticeMailService.getIntance().batchNotice(mn);
		NoticeMailService.getIntance().processBatch();
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