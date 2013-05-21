package edu.fyp.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Application implements Serializable {
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
       
    @Persistent
    private Long appID;
    
    @Persistent
    private String empID;
    
    @Persistent
    private String approvingEmpID;
    
    @Persistent
    private Key formKey;
    
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
    private Key appPath;
    
    @Persistent
	private Set<String> fts;
    
    public Application() {
    	this.status="Processing";
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
    
    public Key getFormKey() {
		return formKey;
	}

	public void setFormKey(Key formKey) {
		this.formKey = formKey;
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

	public void setStatus(String status) {
		this.status = status;
	}

    public String getStatus() {
        return status;
    }
    public Text getFormData() {
		return formData;
	}

	public void setFormData(Text formData) {
		this.formData = formData;
	}

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
	public Key getAppPath() {
		return appPath;
	}

	public void setAppPath(Key appPath) {
		this.appPath = appPath;
	}

	public String getApprovingEmpID() {
		return approvingEmpID;
	}

	public void setApprovingEmpID(String approvingEmpID) {
		this.approvingEmpID = approvingEmpID;
	}

	public Long getAppID() {
		return appID;
	}

	public void setAppID(Long appID) {
		this.appID = appID;
	}

	public Set<String> getFts() {
		return fts;
	}

	public void setFts(Set<String> fts) {
		this.fts = fts;
	}
	
}
