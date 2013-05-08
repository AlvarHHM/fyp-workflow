package edu.fyp.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

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
