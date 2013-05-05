package edu.fyp.bean;

class User {
	protected String userName;
	protected String password;
	protected String empid;

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmpid(String empid) {
		this.empid = empid;
	}

	public String getEmpid() {
		return empid;
	}

}