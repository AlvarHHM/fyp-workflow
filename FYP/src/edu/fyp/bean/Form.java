package edu.fyp.bean;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Form {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String formID;
	@Persistent
	private String formHtml;
	@Persistent
	private String description;
	@Persistent
	private String title;
	@Persistent
	private String version;
	@Persistent
	private Date createDate;
	//	@Persistent
	// private ArrayList<UserGroup> userGroup;
	@Persistent
	private String path;
	@Persistent
	private String constraint;
	@Persistent
	private String createdBy;
	@Persistent
	private Date createdDate;

	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}

	public String getFormHtml() {
		return formHtml;
	}

	public void setFormHtml(String formHtml) {
		this.formHtml = formHtml;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	
	public Key getKey(){
		return key;
	}public void setKey(Key key){
		this.key=key;
	}
}