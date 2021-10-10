package models;

public class SelectArchiverModel {
	String countarchived;
	String countfailed;
	String lastfailed;
	String data;
	
	public String getCountarchived() {
		return countarchived;
	}
	public void setCountarchived(String countarchived) {
		this.countarchived = countarchived;
	}
	
	public String getCountfailed() {
		return countfailed;
	}
	public void setCountfailed(String countfailed) {
		this.countfailed = countfailed;
	}
	
	public String getLastfailed() {
		return lastfailed;
	}
	public void setLastfailed(String lastfailed) {
		this.lastfailed = lastfailed;
	}
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
