package edu.fyp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Text;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.NoticeNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;
import edu.fyp.factory.PathNodeFactory;
import edu.fyp.repository.PathNodeRepository;

public class AddFormPath extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
/*	
		String formID = req.getParameter("formID");
		String version = req.getParameter("version");
		
		Form form = get
		form.setPath(path);*/
		
		
		//for application generate path node test // not for this class - only test
		Text path =new Text(req.getParameter("path"));
		Gson gson = new Gson();
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
		for(int i=0;i<jsonAry.length();i++){
			JSONObject jo;
			try {
				jo = jsonAry.getJSONObject(i);
				out.println(jo.toString());
				String nodeID="";
				nodeID = jo.getString("id");
				pathNodeMap.put(nodeID,pnf.createNode(jo));
				out.println(nodeID+" "+pathNodeMap.get(nodeID));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		storePathNode(pathNodeMap);
		connectPathNode(jsonAry,pathNodeMap);
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
