package edu.fyp.test;

import javax.jdo.PersistenceManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.User;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;
import edu.fyp.search.SearchEmployeeUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/dispatcherServlet-servlet.xml"})
public class UserEmployeeTest {
	
	@Autowired
	UserManager userMan;

    private final LocalServiceTestHelper helper =
        new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

    @Before
    public void setUp() {
        helper.setUp();
    }

    @After
    public void tearDown() {
        helper.tearDown();
    }

  

    @Test
    public void testEmployeeFullTextSearchNickName() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Department dept = new Department();
    	pm.makePersistent(dept);
    	User user = new User();
    	Employee employee = new Employee();
    	employee.setDepartment(dept.getDeptKey());
    	user.setEmployee(employee);
    	employee.setUser(user);
    	
    	employee.setNickName("testNickName");
    	SearchEmployeeUtil.updateIndex(employee);
    	pm.makePersistent(user);
    	pm.close();
	
        Assert.assertTrue(userMan.searchEmployeeByFullText("testNickName").size()!=0);
        
    }
    
    @Test
    public void testEmployeeFullTextSearchSurname() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Department dept = new Department();
    	pm.makePersistent(dept);
    	User user = new User();
    	Employee employee = new Employee();
    	employee.setDepartment(dept.getDeptKey());
    	user.setEmployee(employee);
    	employee.setUser(user);
    	
    	employee.setEngSurname("testName");
    	SearchEmployeeUtil.updateIndex(employee);
    	pm.makePersistent(user);
    	pm.close();
	
        Assert.assertTrue(userMan.searchEmployeeByFullText("testName").size()!=0);
        
    }
    
    @Test
    public void testEmployeeFullTextSearchOtherName() {
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Department dept = new Department();
    	pm.makePersistent(dept);
    	User user = new User();
    	Employee employee = new Employee();
    	employee.setDepartment(dept.getDeptKey());
    	user.setEmployee(employee);
    	employee.setUser(user);
    	
    	employee.setEngOtherName("testName");
    	SearchEmployeeUtil.updateIndex(employee);
    	pm.makePersistent(user);
    	pm.close();
	
        Assert.assertTrue(userMan.searchEmployeeByFullText("testName").size()!=0);
        
    }

    
}