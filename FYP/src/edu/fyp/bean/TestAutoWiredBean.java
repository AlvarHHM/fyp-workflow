package edu.fyp.bean;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fyp.manager.UserManager;

public class TestAutoWiredBean {
	@Autowired
	private UserManager userManager;

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	

}
