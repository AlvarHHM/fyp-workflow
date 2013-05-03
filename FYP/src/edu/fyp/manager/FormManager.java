package edu.fyp.manager;

import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;

public class FormManager {
	public static Form getFormByIDVersion(String formID, String version){
		return FormRepository.getFormByIDVersion(formID, version);
	}
	public static void updateFormPath(String formID, String version, Text path){
		FormRepository.updateFormPath(formID,version,path);
	}
}