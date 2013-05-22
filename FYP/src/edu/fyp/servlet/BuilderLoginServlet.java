package edu.fyp.servlet;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import edu.fyp.bean.BuilderUser;
import edu.fyp.bean.Employee;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;

public class BuilderLoginServlet extends HttpServlet {

	@Autowired
	private UserManager userManager;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		HttpSession session = req.getSession();
		BuilderUser user = null;
		user = userManager.loginBuilder(userName, password);
		if (user != null) {
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Employee emp = pm.getObjectById(Employee.class, user.getEmployee());
			session.setAttribute("BUILDERUSER", user);
			session.setAttribute("BUILDEREMP", emp);
//			System.out.println(emp.getEmpId());
			if (user.getEmployee() != null)
				session.setAttribute("BUILDERDEPT", emp.getDepartment());
			resp.sendRedirect("/formbuilder/showBuilderFormListServlet");
		} else
			resp.sendRedirect("/formbuilder/login.html?error=1");
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
				config.getServletContext());
	}

}
