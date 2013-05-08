package edu.fyp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.node.*;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.PathNodeRepository;

@Service
public class ApplicationManager {
	
	private ApplicationPathGenerator apg;
	
	@Autowired
	public ApplicationManager(ApplicationPathGenerator apg){
		this.apg = apg;
	}
	
	public void applyApplication(Application app) {
		ApplicationRepository.addApplication(app);
		ApplicationPath appPath = apg.generatePath(
				app.getFormID(), app.getVersion());
		ApplicationRepository.updateApplicationPath(app.getKey(), appPath.getKey());
		processApplication(app.getKey());
	}

	public void processApplication(Key key) {
		Application app = ApplicationRepository.getApplication(key);
		ApplicationPath appPath = ApplicationPathRepository.getApplication(app.getAppPath());
		PathNode currentNode=null;
		do{
			currentNode= PathNodeRepository.getNode(appPath.getCurrentNode());
			String currentNodeKind = appPath.getCurrentNode().getKind();
			if(currentNodeKind.equalsIgnoreCase("StartNode")){
				currentNode = (StartNode)currentNode;
			}else if(currentNodeKind.equalsIgnoreCase("ApproveNode")){
				currentNode = (ApproveNode)currentNode;
			}else if(currentNodeKind.equalsIgnoreCase("NoticeNode")){
				currentNode = (NoticeNode)currentNode;
			}else if(currentNodeKind.equalsIgnoreCase("SuccessNode")){
				currentNode = (SuccessNode)currentNode;
			}else if(currentNodeKind.equalsIgnoreCase("FailNode")){
				currentNode = (FailNode)currentNode;
			}
			currentNode.process();
			System.out.println(currentNodeKind);
			System.out.println(currentNode.getState());
			if(currentNode.getState().equalsIgnoreCase("finish")){
				if(currentNodeKind.equalsIgnoreCase("ApproveNode")||currentNodeKind.equalsIgnoreCase("NoticeNode")||currentNodeKind.equalsIgnoreCase("StartNode")){
					appPath.setCurrentNode(((RelayNode)currentNode).getNextNode());
				}
			}
		}while(currentNode.getNodeID().compareTo(appPath.getCurrentNode())!=0);
	}
}