package edu.fyp.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.InheritanceStrategy;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class TestGirl extends TestPeople{
	@Persistent
	private int cup;

	public int getCup() {
		return cup;
	}

	public void setCup(int cup) {
		this.cup = cup;
	}
}


