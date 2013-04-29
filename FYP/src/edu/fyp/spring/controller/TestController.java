package edu.fyp.spring.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.jdo.PersistenceManager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.appengine.api.mail.MailService;
import com.google.appengine.api.mail.MailServiceFactory;

import edu.fyp.bean.node.*;
import edu.fyp.repository.PMF;

@Controller
public class TestController {
	
	
	
	
	PersistenceManager pm = PMF.get().getPersistenceManager();

	@RequestMapping("/test")
	public @ResponseBody
	String test() throws IOException {
		File f = new File("mail-template/NotifyOFComingApproval.html");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = null;
		String line;
		while ((line = in.readLine()) != null) {
			text += line;
		}

		// MailService service = MailServiceFactory.getMailService();
		//
		// MailService.Message msg = new MailService.Message();
		//
		// msg.setSender("mahoihei@gmail.com");
		// msg.setTo("mahoihei@gmail.com");
		//
		// msg.setSubject("testing");
		// msg.setTextBody("testing");
		//
		// try {
		// service.send(msg);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return text;
//		return "Hello World";
	}
}
