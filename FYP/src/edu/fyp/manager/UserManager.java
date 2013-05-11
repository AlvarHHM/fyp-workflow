package edu.fyp.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import edu.fyp.bean.Employee;
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
	
	public List<Employee> searchEmployeeByFullText(String queryString){
		
		return (userRepo.searchEmployeeByFullText(queryString));
		
		
	}
}
