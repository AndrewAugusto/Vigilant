package models;

public class SelectDatabaseConflictModel {
	String datname;
	String conflictlock;
	String conflictdead;
	String data;
	
	public String getDatname() {
		return datname;
	}
	public void setDatname(String datname) {
		this.datname = datname;
	}
	
	public String getConflictlock() {
		return conflictlock;
	}
	public void setConflictlock(String conflictlock) {
		this.conflictlock = conflictlock;
	}
	
	public String getConflictdead() {
		return conflictdead;
	}
	public void setConflictdead(String conflictdead) {
		this.conflictdead = conflictdead;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
