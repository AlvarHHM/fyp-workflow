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
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;
import com.google.gson.Gson;

import edu.fyp.bean.Form;
import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.FailNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;
import edu.fyp.bean.node.SuccessNode;
import edu.fyp.repository.FormRepository;
import edu.fyp.repository.PathNodeRepository;

public class AddFormPath extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		PathNodeRepository.test();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
/*		PathNodeRepository.test();
		String formID = req.getParameter("formID");
		String version = req.getParameter("version");
		
		Form form = get
		form.setPath(path);*/
		
		
		//for application generate path node test // not for this class - only test
		Text path =new Text(req.getParameter("path"));
		Gson gson = new Gson();
		String[] str = gson.fromJson(path.getValue(), String[].class);
		HashMap<String,PathNode> pathNodeMap = new HashMap<String, PathNode>();
		for(int i=0;i<str.length;i++){
			out.println(str[i]);
			JSONObject jo=null;
			try {
				jo = new JSONObject(str[i]);
				out.println(jo.toString());
				String nodeID="";
				nodeID = jo.getString("id");
				pathNodeMap.put(nodeID,createNode(jo));
				out.println(nodeID+" "+pathNodeMap.get(nodeID));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	
	private PathNode createNode(JSONObject json){
		PathNode pathNode =null;
		try {
			if(json.getString("type").equalsIgnoreCase("start")){
				pathNode= new StartNode();
			}else if(json.getString("type").equalsIgnoreCase("approval")){
				pathNode= new ApproveNode();
			}else if(json.getString("type").equalsIgnoreCase("success")){
				pathNode= new SuccessNode();
			}else if(json.getString("type").equalsIgnoreCase("fail")){
				pathNode= new FailNode();
			}else if(json.getString("type").equalsIgnoreCase("notice")){
				pathNode= new FailNode();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pathNode;
	}
}
