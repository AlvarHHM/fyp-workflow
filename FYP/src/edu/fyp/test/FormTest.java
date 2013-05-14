package edu.fyp.test;

import javax.jdo.PersistenceManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import edu.fyp.bean.Department;
import edu.fyp.bean.Employee;
import edu.fyp.bean.Form;
import edu.fyp.manager.FormManager;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PMF;
import edu.fyp.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/dispatcherServlet-servlet.xml"})
public class FormTest {

	
	@Autowired
	FormManager formMan;

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
    public void testGetFormByIDVersion(){
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Form form = new Form();
    	form.setVersion("testVersion");
    	form.setFormID("testId");
    	formMan.addForm(form);
    	Assert.assertTrue(formMan.getFormByIDVersion("testId", "testVersion").getKey().equals(form.getKey()));
    }
    
    @Test
    public void testSearchForm(){
    	PersistenceManager pm = PMF.get().getPersistenceManager();
    	Form form = new Form();
    	form.setFormID("testId");
    	form.setTitle("testTitle");
    	formMan.addForm(form);
    	Assert.assertTrue(formMan.searchForm("title", "testTitle").size()!=0);
    	Assert.assertTrue(formMan.searchForm("formID", "testId").size()!=0);
    	
    	
    }
}
