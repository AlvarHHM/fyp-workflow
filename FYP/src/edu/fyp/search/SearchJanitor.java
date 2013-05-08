package edu.fyp.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.datastore.DatastoreNeedIndexException;
import com.google.appengine.api.datastore.DatastoreTimeoutException;

import edu.fyp.bean.Employee;

public class SearchJanitor {

	private static final Logger log = Logger.getLogger(SearchJanitor.class
			.getName());

	public static final int MAXIMUM_NUMBER_OF_WORDS_TO_SEARCH = 5;

	public static final int MAX_NUMBER_OF_WORDS_TO_PUT_IN_INDEX = 200;

	public static List<Employee> searchEmployee(String queryString,
			PersistenceManager pm) {

		StringBuffer queryBuffer = new StringBuffer();

		queryBuffer.append("SELECT FROM " + Employee.class.getName()
				+ " WHERE ");

		Set<String> queryTokens = SearchJanitorUtils
				.getTokensForIndexingOrQuery(queryString,
						MAXIMUM_NUMBER_OF_WORDS_TO_SEARCH);

		List<String> parametersForSearch = new ArrayList<String>(queryTokens);

		StringBuffer declareParametersBuffer = new StringBuffer();

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
		System.out.println(queryBuffer.toString()+" "+parametersForSearch);
		Query query = pm.newQuery(queryBuffer.toString());

		query.declareParameters(declareParametersBuffer.toString());

		List<Employee> result = null;

		try {
			result = (List<Employee>) query
					.executeWithArray(parametersForSearch.toArray());

		} catch (DatastoreTimeoutException e) {
			log.severe(e.getMessage());
			log.severe("datastore timeout at: " + queryString);// +
																// " - timestamp: "
																// +
																// discreteTimestamp);
		} catch (DatastoreNeedIndexException e) {
			log.severe(e.getMessage());
			log.severe("datastore need index exception at: " + queryString);// +
																			// " - timestamp: "
																			// +
																			// discreteTimestamp);
		}

		return result;

	}

	public static void updateFTSStuffForEmployee(Employee entry) {

		StringBuffer sb = new StringBuffer();
		if (entry.getDepartment() != null) {
			sb.append(entry.getDepartment().getDeptName());
			sb.append(entry.getDepartment().getDeptId());
		}
		sb.append(entry.getEmpId());
		sb.append(entry.getNickName());
		sb.append(entry.getEngSurname());
		sb.append(entry.getEngOtherName());
		if (entry.getTitle() != null)
			sb.append(entry.getTitle().getEngTitle());

		Set<String> new_ftsTokens = SearchJanitorUtils
				.getTokensForIndexingOrQuery(sb.toString().toUpperCase(),
						MAX_NUMBER_OF_WORDS_TO_PUT_IN_INDEX);

		Set<String> ftsTokens = entry.getFts();

		ftsTokens.clear();

		for (String token : new_ftsTokens) {
			ftsTokens.add(token);

		}
	}

}