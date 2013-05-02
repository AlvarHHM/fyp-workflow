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

import edu.fyp.bean.Form;
import edu.fyp.bean.node.ApproveNode;
import edu.fyp.bean.node.FailNode;
import edu.fyp.bean.node.PathNode;
import edu.fyp.bean.node.StartNode;
import edu.fyp.bean.node.SuccessNode;
import edu.fyp.factory.PathNodeFactory;
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
		connectPathNode(jsonAry,pathNodeMap);
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
				System.out.println(i+" tcp check null "+(jo.getString("tcp")==null));
				System.out.println(i+" tcp cehck space"+jo.getString("tcp").equalsIgnoreCase(""));
				System.out.println(i+" tcp "+jo.getString("tcp"));
				System.out.println(i+" fcp check null "+(jo.getString("fcp")==null));
				System.out.println(i+" fcp cehck space"+jo.getString("fcp").equalsIgnoreCase(""));
				System.out.println(i+" tcp "+jo.getString("fcp"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}
