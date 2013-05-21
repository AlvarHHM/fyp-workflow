package edu.fyp.bean.node;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.jdo.annotations.PersistenceCapable;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fyp.bean.Application;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.ApplicationManager;
import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PathNodeRepository;
import edu.fyp.repository.UserRepository;

@PersistenceCapable
public class FailNode extends EndNode{
	
	@Autowired
	ApplicationManager appManager;

	@Autowired
	private PathNodeRepository pathNodeRepo;
	
	@Autowired
	private FormRepository formRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ApplicationRepository appRepo;
	
    public void process(){
    	Application app= appManager.getApplicationByCurrentNode(this.getNodeKey());
		Form form =formRepo.getFormByIDVersion(app.getFormID(), app.getVersion());
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Department dept;
		Employee applier;
		applier = userRepo.queryEmployeeByEmpID(app.getEmpID());
		dept = userRepo.queryDepartmentByDeptKey(applier.getDepartment());
		MailNotice mn = new MailNotice();
		MailBody mb;
		try {
			mb = new MailBody("WEB-INF/mail-template/NotifyOfNotice.html");
			mb.setProperty("applyDate", dateformat.format(app.getApplyDate()));
			mb.setProperty("applier", applier.getEngOtherName()+" "+applier.getEngSurname());
			mb.setProperty("department", dept.getDeptName());
			mb.setProperty("formTitle", form.getTitle());
			mb.setProperty("message", "Your application is approved.");
			mn.setTitle("Application Notice - " + form.getTitle()
					+ " by " + applier.getEngOtherName()+" "+applier.getEngSurname());
			mn.setTo(applier.getEmail());
			mn.setBody(mb);
			NoticeMailService.getIntance().batchNotice(mn);
			NoticeMailService.getIntance().processBatch();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Logger.getAnonymousLogger().warning(e.toString());
		}
    	super.setState("finish");
		pathNodeRepo.updateNodeDate(this);
    	pathNodeRepo.updateNodeState(this, super.getState());
    	appRepo.updateApproveEmp(app.getKey(),null);
    }
}