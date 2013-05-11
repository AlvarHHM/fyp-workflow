package edu.fyp.factory;

import org.springframework.stereotype.Service;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import edu.fyp.bean.node.*;

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
		PathNode pathNode = new StartNode();
		return pathNode;
	}

	private PathNode createApproveNode(JSONObject json) {
		PathNode pathNode = new ApproveNode();
		return pathNode;
	}

	private PathNode createNoticeNode(JSONObject json) {
		PathNode pathNode = (NoticeNode)new NoticeNode();
/*		try {
			JSONObject nodeProps = json.getJSONObject("props");
			if(nodeProps.getString("email")!=null){
				((NoticeNode) pathNode).setEmail(nodeProps.getString("email"));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			System.out.println("error");
			e.printStackTrace();
		}*/
		return pathNode;
	}

	private PathNode createSuccessNode(JSONObject json) {
		PathNode pathNode = new SuccessNode();
		return pathNode;
	}

	private PathNode createFailNode(JSONObject json) {
		PathNode pathNode = new FailNode();
		return pathNode;
	}
}
