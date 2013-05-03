package edu.fyp.manager;

import java.io.IOException;

import edu.fyp.notify.email.MailBody;
import edu.fyp.notify.email.MailNotice;
import edu.fyp.notify.email.NoticeMailService;

public class NotificationManager {
	
	
	public void testNotify() throws IOException{
		MailNotice mn = new MailNotice();
		MailBody mb = new MailBody("/mail-template/NotifyOfComingAproval.html");
		mb.setProperty("userId", "User ID");
		mn.setTitle("test");
		mn.setTo("mahoihei@gmial.com");
		NoticeMailService.getIntance().batchNotice(mn);
		NoticeMailService.getIntance().processBatch();
		
	}

}
