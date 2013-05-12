package edu.fyp.bean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class DocumentBlob {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
	
	@Persistent
    private String name;
	
	@Persistent
    Blob docuement;
	
	public DocumentBlob(){}
	
	public DocumentBlob(String name, Blob docuement) {
		super();
		this.id = id;
		this.name = name;
		this.docuement = docuement;
	}

	public Key getId() {
		return id;
	}

	public void setId(Key id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Blob getDocuement() {
		return docuement;
	}

	public void setDocuement(Blob docuement) {
		this.docuement = docuement;
	}
	
	

}
