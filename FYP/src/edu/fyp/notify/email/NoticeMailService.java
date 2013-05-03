package edu.fyp.notify.email;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
	private List<MailNotice> batch = new ArrayList<MailNotice>();

	private NoticeMailService() {
	}

	public static NoticeMailService getIntance() {
		if (instance == null) {
			synchronized (NoticeMailService.class) {
				if (instance == null)
					return new NoticeMailService();
			}
		}
		return instance;

	}

	public void batchNotice(MailNotice c) {
		batch.add(c);
	}

	public void processBatch() {
		for (MailNotice mail : batch) {
			processMail(mail);
		}
	}

	private void processMail(MailNotice c) {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
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

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
