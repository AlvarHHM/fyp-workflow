package edu.fyp.manager;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.KeyFactory;
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
	
	public Form getFormByFormKey(String formKey){
		return formRepo.getFormByFormKey(KeyFactory.stringToKey(formKey));
	}
	
	public ArrayList<Form> getAllForm(){
		return formRepo.getAllForm();
	}
	
	public void updateFormPath(String formID, String version, Text path){
		formRepo.updateFormPath(formID,version,path);
	}

	public void updateFormStatus(String formID, String version, String status){
		formRepo.updateFormStatus(formID,version,status);
	}
	
	public ArrayList<Form> searchForm(String keyword) {
		return formRepo.searchForm(keyword);
	}

	public void updateFormPath(String formKey, Text path) {
		formRepo.updateFormPath(formKey,path);		
	}

	public ArrayList<Form> searchReleaseForm(String keyword) {
		return formRepo.searchReleaseForm(keyword);
	}

	public ArrayList<Form> getAllReleaseForm() {
		return formRepo.getAllReleaseForm();
	}
}