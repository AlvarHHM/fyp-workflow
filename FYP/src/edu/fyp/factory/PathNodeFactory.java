package edu.fyp.factory;

import edu.fyp.bean.node.*;

public class PathNodeFactory {
	public PathNode createStartNode(String json) {
		PathNode pathNode = new StartNode();
		return pathNode;
	}

	public PathNode createApproveNode(String json) {
		PathNode pathNode = new ApproveNode();
		return pathNode;
	}

	public PathNode createNoticeNode(String json) {
		PathNode pathNode = new NoticeNode();
		return pathNode;
	}

	public PathNode createSuccessNode(String json) {
		PathNode pathNode = new SuccessNode();
		return pathNode;
	}

	public PathNode createFailNode(String json) {
		PathNode pathNode = new FailNode();
		return pathNode;
	}
}
