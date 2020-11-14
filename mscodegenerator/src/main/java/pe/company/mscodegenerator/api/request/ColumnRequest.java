package pe.company.mscodegenerator.api.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ColumnRequest implements Serializable 
{
	private ConnectionDbRequest connectionDb;
	private String table;
	
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
}
