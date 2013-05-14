package edu.fyp.test;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import edu.fyp.manager.UserManager;
import edu.fyp.repository.PMF;
import edu.fyp.repository.UserRepository;

public class UserEmployeeTest {

	
	@Autowired
	UserRepository userRepo;

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
    public void testQueryByDeptAndLevel(){
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	
    }
}
