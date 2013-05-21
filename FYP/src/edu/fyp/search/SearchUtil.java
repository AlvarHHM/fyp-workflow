package edu.fyp.search;

import java.text.SimpleDateFormat;
import java.util.HashSet;

import javax.jdo.PersistenceManager;

import edu.fyp.bean.Application;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.repository.PMF;

public class SearchUtil {
	
	

	public static void updateEmployeeIndex(Employee emp) {
		StringBuffer sb = new StringBuffer();
		if (emp.getDepartment() != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Department dept = pm.getObjectById(Department.class,emp.getDepartment());
			sb.append(dept.getDeptName());
			sb.append(" ");
			sb.append(dept.getDeptId());
			sb.append(" ");
		}
		sb.append(emp.getEmpId());
		sb.append(" ");
		sb.append(emp.getNickName());
		sb.append(" ");
		sb.append(emp.getEngSurname());
		sb.append(" ");
		sb.append(emp.getEngOtherName());
		sb.append(" ");
		if (emp.getTitle() != null) {
			sb.append(emp.getTitle().getEngTitle());
			sb.append(" ");
		}
		emp.setFts(new HashSet<String>());
		for (String token : sb.toString().toUpperCase().split(" ")) {
			emp.getFts().add(token);
		}
	}
	
	public static void updateApplicationIndex(Application app){
		StringBuffer sb = new StringBuffer();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Form form = pm.getObjectById(Form.class,app.getFormKey());
		sb.append(app.getFormID());
		sb.append(" ");
		sb.append(app.getAppID());
		sb.append(" ");
		sb.append(app.getStatus());
		sb.append(" ");
		sb.append(form.getTitle());
		sb.append(" ");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sb.append(sdf.format(app.getApplyDate()));
		sb.append(" ");
		sdf = new SimpleDateFormat("yyyy-MM");
		sb.append(sdf.format(app.getApplyDate()));
		app.setFts(new HashSet<String>());
		for (String token : sb.toString().toUpperCase().split(" ")) {
			app.getFts().add(token);
		}
		
	}
	
	public static void updateFormIndex(Form form){
		StringBuffer sb = new StringBuffer();
		sb.append(form.getDescription());
		sb.append(" ");
		sb.append(form.getFormID());
		sb.append(" ");
		sb.append(form.getTitle());
		sb.append(" ");
		form.setFts(new HashSet<String>());
		for (String token : sb.toString().toUpperCase().split(" ")) {
			form.getFts().add(token);
		}
		
		
	}

}
