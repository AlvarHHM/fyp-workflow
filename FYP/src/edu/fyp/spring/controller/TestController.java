package edu.fyp.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;

import edu.fyp.bean.*;
import edu.fyp.bean.node.*;
import edu.fyp.repository.PMF;

@Controller
public class TestController {

	PersistenceManager pm = PMF.get().getPersistenceManager();

	@RequestMapping("/test")
	public @ResponseBody
	String test() throws Exception {
		
		
		TestBoy boy = new TestBoy();
		TestGirl girl = new TestGirl();
		
		pm.makePersistent(boy);
		pm.makePersistent(girl);
		boolean test = pm.getObjectById(TestPeople.class,boy.getKey()) instanceof TestPeople;
		return String.valueOf(test)  ;
		//return boy.getKey().getNamespace()+" "+boy.getKey().getName()+" "+boy.getKey().getKind();
		// return text;
		//return "Hello World";
	}
}
