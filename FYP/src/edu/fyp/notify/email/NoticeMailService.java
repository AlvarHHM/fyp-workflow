package edu.fyp.notify.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

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

public class NoticeMailService {

	private static NoticeMailService instance;
	private List<MailNotice> batch;

	private NoticeMailService() {
		batch = new ArrayList<MailNotice>();
	}

	public static NoticeMailService getIntance() {
		if (instance == null) {
			synchronized (NoticeMailService.class) {
				if (instance == null)
					instance = new NoticeMailService();
			}
		}
		return instance;

	}

	public void batchNotice(MailNotice c) {
		batch.add(c);
	}

	public void processBatch() {
//		Logger.getAnonymousLogger().warning(String.valueOf(batch.size()));
		for (MailNotice mail : batch) {
			processMail(mail);
			batch.remove(mail);
		}
	}

	private void processMail(MailNotice c) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		Logger.getAnonymousLogger().warning(c.toString());
		try {
			Message msg = new MimeMessage(session);
			Multipart mp = new MimeMultipart();
			msg.setFrom(new InternetAddress("mahoihei@gmail.com",
					"Admin"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
					c.getTo(), "Mr. User"));
			msg.setSubject(c.getTitle());
			MimeBodyPart htmlPart = new MimeBodyPart();
			htmlPart.setContent(c.getBody().getBody(), "text/html");
			mp.addBodyPart(htmlPart);
			msg.setContent(mp);
			Transport.send(msg);
			Logger.getAnonymousLogger().warning(Arrays.toString(msg.getAllRecipients()));
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

	}

}
