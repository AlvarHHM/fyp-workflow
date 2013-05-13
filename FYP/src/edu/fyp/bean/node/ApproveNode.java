package edu.fyp.bean.node;

import java.io.IOException;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Application;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.manager.ApplicationPathGenerator;
import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PathNodeRepository;
import edu.fyp.repository.UserRepository;

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
	
	@Persistent
    private String type;
	
	@Autowired
	private ApplicationManager appManager;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private FormRepository formRepo;
	
	
	public void process(){
		this.setState("approving");
		Application app= appManager.getApplicationByCurrentNode(this.getNodeKey());
		Form form =formRepo.getFormByIDVersion(app.getFormID(), app.getVersion());
		Department dept;
		Employee applier;
		Employee approver = null;
		String approveStr = "/Client/approveAppNode?appKey=" + app.getKey()
				+"&nodeKey=" + this.getNodeKey() + "&approve=";
		
		MailNotice mn = new MailNotice();
		MailBody mb;

		System.out.println(app.getEmpID());
		applier = userRepo.queryEmployeeByEmpID(app.getEmpID());
		dept = userRepo.queryDepartmentByDeptKey(applier.getDepartment());
		if(this.getType().equalsIgnoreCase("super")){
			approver = userRepo.queryEmployeeByDeptKeyAndLevel(
					applier.getDepartment(), applier.getSuperLevel()+200);
			if(approver==null){
				return ;
			}
		}
		
		try {
			mb = new MailBody("WEB-INF/mail-template/NotifyOFComingApproval.html");
			mb.setProperty("applyDate", app.getApplyDate().toString());
			mb.setProperty("applier", applier.getEngOtherName()+applier.getEngOtherName());
			mb.setProperty("department", dept.getDeptName());
			mb.setProperty("formTitle", form.getTitle());
			mb.setProperty("approve", approveStr+"true");
			mb.setProperty("reject", approveStr+"false");
			mn.setTitle("Application Notice - " + form.getTitle()
					+ " by " + applier.getEngOtherName()+applier.getEngOtherName());
			mn.setTo(approver.getEmail());
			NoticeMailService.getIntance().batchNotice(mn);
			NoticeMailService.getIntance().processBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			e.printStackTrace();
		}
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
  
}