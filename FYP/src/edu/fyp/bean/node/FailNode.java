package edu.fyp.bean.node;

import java.util.logging.Logger;

import javax.jdo.annotations.PersistenceCapable;

import org.springframework.beans.factory.annotation.Autowired;

import edu.fyp.repository.PathNodeRepository;

@PersistenceCapable
public class FailNode extends EndNode{
	
	@Autowired
	private PathNodeRepository pathNodeRepo;
	
    public void process(){
    	Logger.getAnonymousLogger().warning("FailNode process - " + super.getNodeKey().toString());
    	super.setState("finish");
    	pathNodeRepo.updateNodeState(this, super.getState());
    }
}