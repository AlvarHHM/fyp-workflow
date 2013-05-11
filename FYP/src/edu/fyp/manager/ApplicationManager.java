package edu.fyp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.node.*;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.PathNodeRepository;

@Service
public class ApplicationManager {

	private ApplicationPathGenerator apg;
	private ApplicationRepository appRepo;
	private ApplicationPathRepository appPathRepo;
	private PathNodeRepository pathNodeRepo;

	@Autowired
	public ApplicationManager(ApplicationPathGenerator apg,
			ApplicationRepository appRepo,
			ApplicationPathRepository appPathRepo,
			PathNodeRepository pathNodeRepo) {
		this.apg = apg;
		this.appRepo = appRepo;
		this.appPathRepo = appPathRepo;
		this.pathNodeRepo = pathNodeRepo;
	}

	public void applyApplication(Application app) {
		appRepo.addApplication(app);
		ApplicationPath appPath = apg.generatePath(app.getFormID(),
				app.getVersion());
		appRepo.updateApplicationPath(app.getKey(), appPath.getKey());
		processApplication(app.getKey());
	}

	public void processApplication(Key key) {
		Application app = appRepo.getApplication(key);
		ApplicationPath appPath = appPathRepo.getApplication(app.getAppPath());
		PathNode currentNode = null;
		do {
			currentNode = pathNodeRepo.getNode(appPath.getCurrentNode());
			String currentNodeKind = appPath.getCurrentNode().getKind();
			currentNode.process();
			System.out.println(appPath.getCurrentNode());
			if (currentNode.getState().equalsIgnoreCase("finish")) {
				if (currentNode instanceof edu.fyp.bean.node.RelayNode) {
					appPath.setCurrentNode(((RelayNode) currentNode)
							.getNextNode());
					appPathRepo.updateCurrentNode(appPath.getKey(),
							((RelayNode) currentNode).getNextNode());
				} else if (currentNode instanceof FailNode) {
					appRepo.updateApplicationStatus(key, "Rejected");
				} else if (currentNode instanceof SuccessNode) {
					appRepo.updateApplicationStatus(key, "Approved");
				}
			}
		} while (currentNode.getNodeKey().compareTo(appPath.getCurrentNode()) != 0);
	}

	public void approveApplicationNode(String appKeyStr, String nodeKeyStr,
			boolean approve) {
		Key appKey = KeyFactory.stringToKey(appKeyStr);
		Key nodeKey = KeyFactory.stringToKey(nodeKeyStr);
		Application app = appRepo.getApplication(appKey);
		ApplicationPath appPath = appPathRepo.getApplication(app.getAppPath());
		ApproveNode pathNode = (ApproveNode) pathNodeRepo.getNode(nodeKey);
		System.out.println(pathNode.getNodeKey());
		System.out.println(appPath.getCurrentNode());
		if (pathNode.getNodeKey().equals(appPath.getCurrentNode())) {
			System.out.println("here ?");
			pathNode.approve(approve);
			pathNodeRepo.updateApproveNextNode(pathNode, pathNode.getNextNode());//Update datastore
			appPathRepo.updateCurrentNode(appPath.getKey(),
					pathNode.getNextNode());
			processApplication(app.getKey());
		}
	}
}