package edu.fyp.bean;

import java.util.ArrayList;
import java.util.Date;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

<<<<<<< HEAD
@PersistenceCapable
public class Form {

	@Persistent
    private String formID;
    private String form;
    private String version;
    private Date createDate;
    //private ArrayList<UserGroup> userGroup;
    private String path;
    private String constraint;
    private String createdBy;
    private Date createdDate;

    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
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
=======
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
	private String form;
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

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
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
<<<<<<< HEAD
>>>>>>> dc55d5cefc194931d9293df2a6a07cc328e93d0e
=======
	public Key getKey(){
		return key;
	}public void setKey(Key key){
		this.key=key;
	}
>>>>>>> 818f201bbdd9305b48dced4868de3d9476837ba7
}