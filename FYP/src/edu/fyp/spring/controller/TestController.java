package edu.fyp.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Employee;
import edu.fyp.bean.TestAutoWiredBean;
import edu.fyp.bean.User;
import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.FailNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;
import edu.fyp.bean.node.SuccessNode;
import edu.fyp.manager.ApplicationMonitorManager;
import edu.fyp.manager.NotificationManager;
import edu.fyp.manager.UserManager;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.PMF;

@Controller
public class TestController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private NotificationManager notificationManger;

	@Autowired
	private ApplicationPathRepository appPathRepo; 
	
	@Autowired
	private ApplicationMonitorManager appMonManager;
	
	@RequestMapping("/test.do")
	public @ResponseBody
	String test() throws Exception {

		PersistenceManager pm = PMF.get().getPersistenceManager();

		User user = new User();
		user.setUserName("mahoihei");
		user.setPassword("mahoihei");

		Employee employee = new Employee();
		employee.setNickName("Alvar");
		employee.setEngSurname("Ma");
		employee.setEngOtherName("Hoi Hei");
		employee.setEmpId("H001");
		employee.updateFullTextSearchIndex();
		user.setEmployee(employee);
		employee.setUser(user);
		pm.makePersistent(user);
		pm.close();

		return userManager.searchEmployeeByFullText("hoi").toString();

		// return "Hello World";
	}

	@RequestMapping("/test2")
	public @ResponseBody
	String test2() throws Exception {
		TestAutoWiredBean bean = new TestAutoWiredBean();
		applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);

		return String.valueOf(bean.getUserManager()
				.searchEmployeeByFullText("ma").toString());
	}

	@RequestMapping("testNotice")
	public @ResponseBody
	String testNotice() throws Exception {
		this.notificationManger.testNotify();
		return "hello";
	}
	
	@RequestMapping("/test3")
	public @ResponseBody
	String test3() throws Exception {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		try {
			Message msg = new MimeMessage(session);
			Multipart mp = new MimeMultipart();
			msg.setFrom(new InternetAddress("mahoihei@gmail.com",
					"Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					"mahoihei@gmail.com", "Mr. User"));
			msg.setSubject("test");
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent("<html></html>", "text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);
			

		} catch (AddressException e) {
			e.printStackTrace();
			Logger.getAnonymousLogger().warning(e.getMessage());
		} catch (MessagingException e) {
			e.printStackTrace();
			Logger.getAnonymousLogger().warning(e.getMessage());
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getAnonymousLogger().warning(e.getMessage());
		}
		return "hello";
	}
	
	@RequestMapping("/testCurrnet")
	public @ResponseBody
	String testCurrnet() throws Exception {
		String appKeyStr ="ag5zfndvcmtmbG93LWZ5cHISCxILQXBwbGljYXRpb24Yky8M";
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		return appMonManager.getApplicationPath(appKey).toString();
	}
}
