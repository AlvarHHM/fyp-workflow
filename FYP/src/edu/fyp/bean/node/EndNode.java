package edu.fyp.bean.node;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.InheritanceStrategy;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public class EndNode extends PathNode{

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}
}