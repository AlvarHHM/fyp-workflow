package edu.fyp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.fyp.bean.User;
import edu.fyp.repository.UserRepository;

@Service
public class UserManager {
	
	private UserRepository userRepo;
	
	@Autowired
	public UserManager(UserRepository userRepo){
		this.userRepo = userRepo;
	}
	
	public String test(){
		return "mahoihei";
	}
	
	public User login(String userName,String password){
		User result = userRepo.queryUserByUserName(userName);
		return result!=null&&result.getPassword().equals(password)?result:null;
	}
}
