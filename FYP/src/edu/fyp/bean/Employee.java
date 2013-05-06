package edu.fyp.bean;

import java.io.Serializable;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Employee implements Serializable{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected Key empId;
	@Persistent
	protected int superLevel;
	@Persistent
	protected Department department;
	@Persistent(mappedBy = "employee")
	private User user;

	public void setEmpId(Key empId) {
		this.empId = empId;
	}

	public Key getEmpId() {
		return empId;
	}

	public void setSuperLevel(int superLevel) {
		this.superLevel = superLevel;
	}

	public int getSuperLevel() {
		return superLevel;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
}