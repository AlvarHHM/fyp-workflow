package edu.fyp.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.User;

@Repository
public class UserRepository {

	public User queryUserByUserName(String userName) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		q.setFilter("userName == inputUserName");
		q.declareParameters("java.lang.String inputUserName");
		List result = ((List) q.execute(userName));
		return (User) (result.size() != 0 ? result.get(0) : null);

	}
	
//	public Employee queryUserByDeptIdAndLevel(String deptId,int level){
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		Query query = pm.newQuery(Employee.class, "superLevel == levelParam && department.deptId == deptParam");
//		query.declareParameters("int levelParam,String deptParam");
//		List<Employee> result = (List<Employee>) query.execute(level,deptId);
//		return result.size()!=0?result.get(0):null;
//	}
	
	public Employee queryEmployeeByDeptKeyAndLevel(Key deptKey,int level){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query query = pm.newQuery(Employee.class);
		query.setFilter("superLevel == levelParam && department == deptParam");
		query.declareParameters("int levelParam,com.google.appengine.api.datastore.Key deptParam");
		List<Employee> result = (List<Employee>) query.execute(level,deptKey);
		return result.size()!=0?result.get(0):null;
	}
	
	public Department queryDepartmentByDeptKey(Key deptKey){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Department dept = pm.getObjectById(Department.class, deptKey);
		return dept;
	}
	
	public Employee queryEmployeeByEmpID(String empIDStr){
		PersistenceManager pm = PMF.get().getPersistenceManager();
		System.out.println(empIDStr);
		Query query = pm.newQuery(Employee.class);
		query.setFilter(" empId == empIDStr");
		query.declareParameters("String empIDStr");
		List<Employee> result = (List<Employee>) query.execute(empIDStr);
		return result.size()!=0?result.get(0):null;
	}

	public List<Employee> searchEmployeeByFullText(String queryString) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		StringBuffer queryBuffer = new StringBuffer();

		queryBuffer.append("SELECT FROM " + Employee.class.getName()
				+ " WHERE ");

		StringBuffer declareParametersBuffer = new StringBuffer();

		Set<String> queryTokens = new HashSet<String>();
		for (String token : queryString.split(" ")) {
			queryTokens.add(token.trim().toUpperCase());
		}
		List<String> parametersForSearch = new ArrayList<String>(queryTokens);
		int parameterCounter = 0;

		while (parameterCounter < queryTokens.size()) {

			queryBuffer.append("fts == param" + parameterCounter);
			declareParametersBuffer.append("String param" + parameterCounter);

			if (parameterCounter + 1 < queryTokens.size()) {
				queryBuffer.append(" && ");
				declareParametersBuffer.append(", ");

			}

			parameterCounter++;

		}

		Query query = pm.newQuery(queryBuffer.toString());

		query.declareParameters(declareParametersBuffer.toString());

		List<Employee> result = (List<Employee>) query
				.executeWithArray(parametersForSearch.toArray());

		return result;
	}
	
	

}
