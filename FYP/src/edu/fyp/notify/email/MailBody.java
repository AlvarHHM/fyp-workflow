package edu.fyp.notify.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MailBody {
	private String template = null;
	public MailBody(String path) throws IOException{
		File f = new File(path);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String text = "";
		String line;
		while ((line = in.readLine()) != null) {
			text += line;
		}
		
		template = text;
	}
	
	public void setProperty(String name,String value){
		template.replaceAll("\\{"+name+"\\}", value); 
	}
	
}
