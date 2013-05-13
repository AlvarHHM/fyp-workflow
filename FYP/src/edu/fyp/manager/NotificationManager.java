package edu.fyp.manager;

import java.io.IOException;

import org.springframework.stereotype.Service;

import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;

@Service
public class NotificationManager {
	
	
	public void testNotify() throws IOException{
		MailNotice mn = new MailNotice();
		MailBody mb = new MailBody("WEB-INF/mail-template/NotifyOfNotice.html");
		mb.setProperty("userId", "User ID");
		mn.setTitle("test");
		mn.setTo("mahoihei@gmial.com");
		mn.setBody(mb);
		NoticeMailService.getIntance().batchNotice(mn);
		NoticeMailService.getIntance().processBatch();
		
	}

}
