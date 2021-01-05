package pe.company.mscodegenerator.api.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ColumnRequest implements Serializable 
{
	private ConnectionDbRequest connectionDb;
	private String table;
	private String programmingLanguage;	
	
	public ConnectionDbRequest getConnectionDb() {
		return connectionDb;
	}
	public void setConnectionDb(ConnectionDbRequest connectionDb) {
		this.connectionDb = connectionDb;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	
}
