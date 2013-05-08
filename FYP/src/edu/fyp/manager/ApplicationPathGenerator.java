package edu.fyp.manager;

import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Form;
import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.NoticeNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;
import edu.fyp.factory.PathNodeFactory;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.PathNodeRepository;

@Service
public class ApplicationPathGenerator {
	
	private FormManager formManager;
	private ApplicationPathRepository appPathRepo;
	
	@Autowired
	public ApplicationPathGenerator(FormManager formManager,ApplicationPathRepository appPathRepo){
		this.formManager = formManager;
		this.appPathRepo = appPathRepo;
	}
	
	public ApplicationPath generatePath(String formID, String version) {
		Form form = formManager.getFormByIDVersion(formID, version);
		Text path =form.getPath();
		Gson gson = new Gson();
		ApplicationPath appPath= new ApplicationPath();
		String[] str = gson.fromJson(path.getValue(), String[].class);
		HashMap<String,PathNode> pathNodeMap = new HashMap<String, PathNode>();
		PathNodeFactory pnf = new PathNodeFactory();
		JSONArray jsonAry = new JSONArray();
		try {
			jsonAry = strToJSONArray(str);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//create hashmap
		for(int i=0;i<jsonAry.length();i++){
			JSONObject jo;
			try {
				jo = jsonAry.getJSONObject(i);
				String nodeID="";
				nodeID = jo.getString("id");
				pathNodeMap.put(nodeID,pnf.createNode(jo));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		storePathNode(pathNodeMap);
		connectPathNode(jsonAry,pathNodeMap);
		//find start node and set to application path
		for(int i=0;i<jsonAry.length();i++){
			JSONObject jo;
			try {
				jo = jsonAry.getJSONObject(i);
				if(jo.getString("type").equalsIgnoreCase("start")){
					appPath.setStartNode(pathNodeMap.get(jo.get("id")).getNodeID());
					appPath.setCurrentNode(pathNodeMap.get(jo.get("id")).getNodeID());
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		appPathRepo.addApplicationPath(appPath);
		return appPath;
	}
	private void storePathNode(HashMap<String, PathNode> pathNodeMap) {
		for(Object key:pathNodeMap.keySet()){
			PathNodeRepository.addPathNode(pathNodeMap.get(key));
			System.out.println(pathNodeMap.get(key).getNodeID().toString());
		}
	}
	private JSONArray strToJSONArray(String[] str) throws JSONException{
		JSONArray jsonAry= new JSONArray();
		for(int i=0;i<str.length;i++){
			JSONObject jo = new JSONObject(str[i]);
			jsonAry.put(jo);
		}
		return jsonAry;
	}
	private void connectPathNode(JSONArray jsonAry, HashMap<String,PathNode> pathNodeMap){
		for(int i=0;i<jsonAry.length();i++){
			try {
				JSONObject jo = jsonAry.getJSONObject(i);
				PathNode pathNode = pathNodeMap.get(jo.get("id"));
				String tcp = jo.getString("tcp");
				String fcp = jo.getString("fcp");

				try{
				tcp=tcp.substring(tcp.indexOf('"')+1, tcp.lastIndexOf('"'));
				}catch(Exception e){
					tcp=null;
				}
				try{
				fcp=fcp.substring(fcp.indexOf('"')+1, fcp.lastIndexOf('"'));
				}catch(Exception e){
					fcp=null;
				}

				if(jo.getString("type").equalsIgnoreCase("approval")){
					if(tcp!=null){
						System.out.println(jo.getString("id")+" tto "+tcp);
						if(pathNodeMap.get(tcp)!=null){
							PathNodeRepository.updateApproveNextTrueNode((ApproveNode)pathNode,pathNodeMap.get(tcp).getNodeID());
						}
					}
					if(fcp!=null){
						System.out.println(jo.getString("id")+" fto "+fcp);
						if(pathNodeMap.get(fcp)!=null){
							PathNodeRepository.updateApproveNextTrueNode((ApproveNode)pathNode,pathNodeMap.get(fcp).getNodeID());
						}
					}
				}else if(jo.getString("type").equalsIgnoreCase("notice")){
					if(tcp!=null){
						System.out.println(jo.getString("id")+" tto "+tcp);
						if(pathNodeMap.get(tcp)!=null){
							PathNodeRepository.updateNoticeNextNode((NoticeNode)pathNode,pathNodeMap.get(tcp).getNodeID());
						}
					}
				}else if(jo.getString("type").equalsIgnoreCase("start")){
					if(tcp!=null){
						System.out.println(jo.getString("id")+" tto "+tcp);
						if(pathNodeMap.get(tcp)!=null){
							PathNodeRepository.updateStartNextNode((StartNode)pathNode,pathNodeMap.get(tcp).getNodeID());
						}
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}