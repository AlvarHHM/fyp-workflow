package edu.fyp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.PathNodeRepository;

@Service
public class ApplicationMonitorManager{

	private ApplicationPathGenerator apg;
	private ApplicationRepository appRepo;
	private ApplicationPathRepository appPathRepo;
	private PathNodeRepository pathNodeRepo;
	private ApplicationContext applicationContext;

	@Autowired
	public ApplicationMonitorManager(ApplicationPathGenerator apg,
			ApplicationRepository appRepo,
			ApplicationPathRepository appPathRepo,
			PathNodeRepository pathNodeRepo,
			ApplicationContext applicationContext) {
		this.apg = apg;
		this.appRepo = appRepo;
		this.appPathRepo = appPathRepo;
		this.pathNodeRepo = pathNodeRepo;
		this.applicationContext = applicationContext;
	}

	public Application getApplication(String empID){
            Application app=new Application();
            return app;
	}
}