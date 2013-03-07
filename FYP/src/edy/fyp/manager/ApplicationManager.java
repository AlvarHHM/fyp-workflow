package edy.fyp.manager;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.PathNode.EndNode;
import edu.fyp.bean.PathNode.PathNode;
import edu.fyp.bean.PathNode.RelayNode;



public class ApplicationManager {

    public void processApplicationPath(String appID) {
        //get application path
        ApplicationPath ap = new ApplicationPath();
        PathNode currentNode;
        while (!((currentNode = ap.getCurrentNode()) instanceof EndNode)) {
            currentNode=currentNode;
            currentNode.process();
            String state = currentNode.getState();
            if (state.equalsIgnoreCase("finish")) {
                if(currentNode instanceof RelayNode){
                    ap.setCurrentNode(((RelayNode)currentNode).getNextNode());
                }
            }
        }
    }

    public void processApproveNode(String appID, String nodeID, Boolean approve) {
    }

    public void processCompositeNode(String appID, String nodeID, Boolean approve) {
    }

    public void processRequest(String appID, String nodeID, Boolean approve) {
    }

    public void createApplication(String formID, String empID, String applicationData) {
    }

    public Application getApplication(String appID) {
        return null;
    }

    public ApplicationPath getApplicationPath(String appID) {
        Application app = getApplication(appID);
        ApplicationPath appPath = app.getApplicationPath();
        return appPath;
    }
}