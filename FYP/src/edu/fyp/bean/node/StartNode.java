package edu.fyp.bean.node;

import java.util.logging.Logger;

import javax.jdo.annotations.PersistenceCapable;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.appengine.api.datastore.Key;

import edu.fyp.repository.PathNodeRepository;

@PersistenceCapable
public class StartNode extends RelayNode {
	
	@Autowired
	private PathNodeRepository pathNodeRepo;

	public void process(){
        Logger.getAnonymousLogger().warning("Start process - " + this.getNodeKey());
		this.setState("finish");
		pathNodeRepo.updateNodeDate(this);
		pathNodeRepo.updateNodeState(this, this.getState());
	}
}
