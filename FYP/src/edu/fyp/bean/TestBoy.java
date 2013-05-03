package edu.fyp.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class TestBoy extends TestPeople {
	@Persistent
	private long length;

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}
}
