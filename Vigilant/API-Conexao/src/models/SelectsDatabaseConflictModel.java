package models;

public class SelectsDatabaseConflictModel {
	String calls;
	String query;
	String tempoMedio;
	String data;
	
	public String getCalls() {
		return calls;
	}
	public void setCalls(String calls) {
		this.calls = calls;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getTempoMedio() {
		return tempoMedio;
	}
	public void setTempoMedio(String tempoMedio) {
		this.tempoMedio = tempoMedio;
	}
	
	
}
