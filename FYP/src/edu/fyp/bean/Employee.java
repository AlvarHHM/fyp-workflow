package edu.fyp.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Employee implements Serializable {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key empKey;
	@Persistent
	private int superLevel;
	@Persistent
	private Key department;
	@Persistent
	private String superId;
	@Persistent(mappedBy = "employee")
	private User user;
	@Persistent
	private String empId;
	@Persistent
	private String engSurname;
	@Persistent
	private String engOtherName;
	@Persistent
	private String chiSurname;
	@Persistent
	private String chiOthername;
	@Persistent
	private String nickName;
	@Persistent
	private Title title;
	@Persistent
	private String email;
	@Persistent
	private Set<String> fts;
	
	public Employee(){
		fts = new HashSet<String>();
//		SearchJanitor.updateFTSStuffForEmployee(this);
	}

	public Key getEmpKey() {
		return empKey;
	}

	public void setEmpKey(Key empKey) {
		this.empKey = empKey;
		
	}

	public int getSuperLevel() {
		return superLevel;
	}

	public void setSuperLevel(int superLevel) {
		this.superLevel = superLevel;
	}

	public Key getDepartment() {
		return department;
	}

	public void setDepartment(Key department) {
		this.department = department;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEngSurname() {
		return engSurname;
	}

	public void setEngSurname(String engSurname) {
		this.engSurname = engSurname;
	}

	public String getEngOtherName() {
		return engOtherName;
	}

	public void setEngOtherName(String engOtherName) {
		this.engOtherName = engOtherName;
	}

	public String getChiSurname() {
		return chiSurname;
	}

	public void setChiSurname(String chiSurname) {
		this.chiSurname = chiSurname;
	}

	public String getChiOthername() {
		return chiOthername;
	}

	public void setChiOthername(String chiOthername) {
		this.chiOthername = chiOthername;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<String> getFts() {
		return fts;
	}

	public void setFts(Set<String> fts) {
		this.fts = fts;
	}
	
	

	public enum Title {
		COOFF("CHIEF OPERATIONS OFFICER", "營運總監", 100), SD("SALES DIRECTOR",
				"營業董事", 80), DM("DISTRICT MANAGER", "區域經理", 60), SM(
				"SALES MANAGER", "營業經理", 40), ACMGR("ACCOUNT MANAGER", "客戶經理",
				20), PC("PROPERTY CONSULTANT", "物業顧問", 10);
		private String engTitle;
		private String chiTitle;
		private int titilePoint;

		private Title(String engTitle, String chiTitle, int titilePoint) {
			this.engTitle = engTitle;
			this.chiTitle = chiTitle;
			this.titilePoint = titilePoint;
		}

		public String getEngTitle() {
			return engTitle;
		}

		public String getChiTitle() {
			return chiTitle;
		}

		public int getTitilePoint() {
			return titilePoint;
		}

	}

}