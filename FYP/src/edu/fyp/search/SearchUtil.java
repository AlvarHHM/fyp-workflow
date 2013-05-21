package edu.fyp.search;

import java.util.HashSet;

import javax.jdo.PersistenceManager;

import edu.fyp.bean.Application;
import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
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
		sb.append(app.getAppID());
		sb.append(" ");
		sb.append(app.getEmpID());
		sb.append(" ");
		sb.append(app.getStatus());
		sb.append(" ");
		sb.append(app.getStatus());
		sb.append(" ");
		
		
	}

}
