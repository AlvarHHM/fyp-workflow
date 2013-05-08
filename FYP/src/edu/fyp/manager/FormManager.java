package edu.fyp.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Text;

import edu.fyp.bean.Form;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.UserRepository;

@Service
public class FormManager {
	
	private FormRepository formRepo;
	
	@Autowired
	public FormManager(FormRepository formRepo){
		this.formRepo = formRepo;
	}
	
	public void addForm(Form form) {
		formRepo.addForm(form);
	}
	
	public Form getFormByIDVersion(String formID, String version){
		return formRepo.getFormByIDVersion(formID, version);
	}
	
	public ArrayList<Form> getAllForm(){
		return formRepo.getAllForm();
	}
	
	public void updateFormPath(String formID, String version, Text path){
		formRepo.updateFormPath(formID,version,path);
	}

	public ArrayList<Form> searchForm(String search, String keyword) {
		return formRepo.searchForm(search, keyword);
	}
}