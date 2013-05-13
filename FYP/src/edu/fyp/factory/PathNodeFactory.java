package edu.fyp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import edu.fyp.bean.node.*;
import edu.fyp.repository.UserRepository;

public class PathNodeFactory {
	
	public PathNode createNode(JSONObject json) throws JSONException{
		PathNode pathNode=null;
		if(json.getString("type").equalsIgnoreCase("start")){
			pathNode= createStartNode(json);
		}else if(json.getString("type").equalsIgnoreCase("approval")){
			pathNode= createApproveNode(json);
		}else if(json.getString("type").equalsIgnoreCase("notice")){
			pathNode= createNoticeNode(json);
		}else if(json.getString("type").equalsIgnoreCase("success")){
			pathNode= createSuccessNode(json);
		}else if(json.getString("type").equalsIgnoreCase("fail")){
			pathNode= createFailNode(json);
		}
		if(pathNode!=null){
			pathNode.setNodeID(json.getString("id"));
		}
		return pathNode;
	}
	private PathNode createStartNode(JSONObject json) {
		StartNode pathNode = new StartNode();
		return pathNode;
	}

	private PathNode createApproveNode(JSONObject json) {
		ApproveNode pathNode = new ApproveNode();
		try {
			JSONObject nodeProps = json.getJSONObject("props");
			if(nodeProps.getString("approval-type").equalsIgnoreCase("super")){
				pathNode.setType(nodeProps.getString("approval-type"));
			}else if(nodeProps.getString("approval-type").equalsIgnoreCase("lud")){
				pathNode.setType(nodeProps.getString("approval-type"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathNode;
	}

	private PathNode createNoticeNode(JSONObject json) {
		NoticeNode pathNode = new NoticeNode();
		try {
			JSONObject nodeProps = json.getJSONObject("props");
			if(nodeProps.getString("notice-message")!=null){
				pathNode.setNoticeMessage(nodeProps.getString("notice-message"));
			}
			if(nodeProps.getString("notice-type").equalsIgnoreCase("email")){
				pathNode.setType(nodeProps.getString("notice-type"));
				pathNode.setEmail(nodeProps.getString("notice-email"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathNode;
	}

	private PathNode createSuccessNode(JSONObject json) {
		SuccessNode pathNode = new SuccessNode();
		return pathNode;
	}

	private PathNode createFailNode(JSONObject json) {
		FailNode pathNode = new FailNode();
		return pathNode;
	}
}
