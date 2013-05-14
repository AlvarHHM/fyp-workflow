package edu.fyp.bean;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Form implements Serializable{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	@Persistent
	private String formID;
	@Persistent
	private String version;
	@Persistent
	private String title;
	@Persistent
	private Text formHtml;
	@Persistent
	private String description;
	//	@Persistent
	// private ArrayList<UserGroup> userGroup;
	@Persistent
	private Text path;
	@Persistent
	private String constraint;
	@Persistent
	private String status;
	@Persistent
	private String createdBy;
	@Persistent
	private Date createdDate;

	public Form(){
		this.status="Editing";
		this.path = new Text("");
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFormID() {
		return formID;
	}

	public void setFormID(String formID) {
		this.formID = formID;
	}

	public Text getFormHtml() {
		return formHtml;
	}

	public void setFormHtml(Text formHtml) {
		this.formHtml = formHtml;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Text getPath() {
		return path;
	}

	public void setPath(Text path) {
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