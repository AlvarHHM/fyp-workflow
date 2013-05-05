package edu.fyp.manager;

import java.util.ArrayList;

import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;

public class FormManager {
	public static Form getFormByIDVersion(String formID, String version){
		return FormRepository.getFormByIDVersion(formID, version);
	}
	
	public static ArrayList<Form> getAllForm(){
		return FormRepository.getAllForm();
	}
	
	public static void updateFormPath(String formID, String version, Text path){
		FormRepository.updateFormPath(formID,version,path);
	}

	public static ArrayList<Form> searchForm(String search, String keyword) {
		return FormRepository.searchForm(search, keyword);
	}
}