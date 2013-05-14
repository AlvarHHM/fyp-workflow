package edu.fyp.manager;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import edu.fyp.bean.Application;
import edu.fyp.bean.ApplicationPath;
import edu.fyp.bean.Employee;
import edu.fyp.bean.node.*;
import edu.fyp.repository.ApplicationPathRepository;
import edu.fyp.repository.ApplicationRepository;
import edu.fyp.repository.PathNodeRepository;
import edu.fyp.repository.UserRepository;

@Service
public class ApplicationMonitorManager {

	private ApplicationRepository appRepo;
	private ApplicationPathRepository appPathRepo;
	private PathNodeRepository pathNodeRepo;
	private ApplicationContext applicationContext;
	private UserRepository userRepo;

	@Autowired
	public ApplicationMonitorManager(
			ApplicationRepository appRepo,
			ApplicationPathRepository appPathRepo,
			PathNodeRepository pathNodeRepo,
			ApplicationContext applicationContext, UserRepository userRepo) {
		this.appRepo = appRepo;
		this.appPathRepo = appPathRepo;
		this.pathNodeRepo = pathNodeRepo;
		this.applicationContext = applicationContext;
		this.userRepo = userRepo;
	}

	public String getApplicationPath(Key appKey) {
		Application app = appRepo.getApplication(appKey);
		ApplicationPath appPath = appPathRepo.getApplicationPath(app
				.getAppPath());
		if(appPath == null)return "";
		PathNode currentNode = pathNodeRepo.getNode(appPath.getStartNode());
		JSONArray jsonAry = new JSONArray();
		while (currentNode.getState().equalsIgnoreCase("finish")
				&& !(currentNode instanceof EndNode)) {
			JSONObject jo = new JSONObject();
			try {
				jo.put("Node-Type", currentNode.getNodeKey().getKind());
				long millisecond = currentNode.getProcessDate().getTime();
				jo.put("Timestamp",millisecond);
				jo.put("Node-ID", currentNode.getNodeID());
				if (currentNode.getNodeKey().getKind()
						.equalsIgnoreCase("ApproveNode")) {
					Employee approver = userRepo
							.queryEmployeeByEmpID(((ApproveNode) currentNode)
									.getApproverID());
					jo.put("approver",
							approver.getEmpId() + " - "
									+ approver.getEngOtherName() + " "
									+ approver.getEngSurname());
				} else {
					jo.put("approver", "");
				}
				jsonAry.put(jo);
			} catch (JSONException e) {
				Logger.getAnonymousLogger().warning(e.toString());
			}
			if (currentNode instanceof RelayNode) {
				currentNode = pathNodeRepo.getNode(((RelayNode) currentNode)
						.getNextNode());
			}
		}
		if (currentNode instanceof EndNode && currentNode.getState().equalsIgnoreCase("finish")) {
			JSONObject jo = new JSONObject();
			try {
				jo.put("Node-Type", currentNode.getNodeKey().getKind());
				jo.put("approver", "");
				jo.put("Node-ID", currentNode.getNodeID());
				jsonAry.put(jo);
			} catch (JSONException e) {
				Logger.getAnonymousLogger().warning(e.toString());
			}
		}
		return jsonAry.toString();
	}
}