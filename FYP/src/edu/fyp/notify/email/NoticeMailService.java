package edu.fyp.notify.email;

import java.util.ArrayList;
import java.util.List;

public class NoticeMailService {
	
	private static NoticeMailService instance;
	private List<MailNotice> batch = new ArrayList<MailNotice>();
	private NoticeMailService(){}
	
	public static NoticeMailService getIntance(){
		if(instance == null){
			synchronized(NoticeMailService.class){
				if(instance == null)
					return new NoticeMailService();
			}
		}
		return instance;
		
	}
	
	public void batchNotice(MailNotice c){
		
	}
	

}
