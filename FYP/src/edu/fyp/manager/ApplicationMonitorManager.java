package edu.fyp.manager;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;

public class ApplicationMonitorManager{
	public Application getApplication(String empID){
            Application app=new Application();
            return app;
	}
	public ApplicationPath getApplicationPath(String appID){
            return getApplication(appID).getApplicationPath();
	}
	public String getApplicationDetail(String appID){
            return null;
	}
}