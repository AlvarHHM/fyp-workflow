package edu.fyp.bean;


import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Application {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String empID;
    @Persistent
    private String formID;
	@Persistent
    private String version;
    @Persistent
    private String status;
    @Persistent
    private Text formData;
    @Persistent
    private Date applyDate;
    @Persistent
    private ApplicationPath appPath;

    public Text getFormData() {
		return formData;
	}

	public void setFormData(Text formData) {
		this.formData = formData;
	}

	public ApplicationPath getAppPath() {
		return appPath;
	}

	public void setAppPath(ApplicationPath appPath) {
		this.appPath = appPath;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    public Application() {
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
    
    public String getFormID() {
        return formID;
    }

    public void setFormID(String formID) {
        this.formID = formID;
    }
    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    public void setApplicationPath(ApplicationPath appPath){
    	this.appPath = appPath;
    }
    public ApplicationPath getApplicationPath(){
        return appPath;
    }
}
