package edu.fyp.notify.email;

public class MailNotice {
	private MailBody body;
	private String to;
	private String title;
	
	public MailBody getBody() {
		return body;
	}
	public void setBody(MailBody body) {
		this.body = body;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
