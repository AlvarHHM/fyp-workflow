package edu.fyp.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import edu.fyp.bean.User;

@Repository
public class UserRepository {
	
	public User queryUserByUserName(String userName){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		q.setFilter("userName == inputUserName");
		q.declareParameters("java.lang.String inputUserName");
		List result = ((List)q.execute(userName));
		return (User) (result.size()!=0?result.get(0):null);
		
	}

}
