package models;

public class SelectDbcommitModel {

	String data;
	String dbname;
	String commit;
	String rollback;
	String blkread;
	String blkhit;
	String conflicts;
	String deadlocks;
	
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	
	public String getCommit() {
		return commit;
	}
	public void setCommit(String commit) {
		this.commit = commit;
	}
	
	public String getRollback() {
		return rollback;
	}
	public void setRollback(String rollback) {
		this.rollback = rollback;
	}
	
	public String getBlkread() {
		return blkread;
	}
	public void setBlkread(String blkread) {
		this.blkread = blkread;
	}
	
	public String getBlkhit() {
		return blkhit;
	}
	public void setBlkhit(String blkhit) {
		this.blkhit = blkhit;
	}
	
	public String getConflicts() {
		return conflicts;
	}
	public void setConflicts(String conflicts) {
		this.conflicts = conflicts;
	}
	
	public String getDeadlocks() {
		return deadlocks;
	}
	public void setDeadlocks(String deadlocks) {
		this.deadlocks = deadlocks;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
