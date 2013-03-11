package edu.fyp.bean;


import java.util.Date;


public class Application {
/*hahahssssaddd*/
    private String appID;
    private String formData;
    private Date applyDate;
    private String empID;
    private String formID;
    private String status;
    private String version;
    private ApplicationPath appPath;

    public Application() {
    }

    public String getStatus() {
        return status;
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }
    public ApplicationPath getApplicationPath(){
        return appPath;
    }
}
