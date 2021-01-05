package pe.company.mscodegenerator.api.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TableRequest implements Serializable
{
	private String dbType;
	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	private String connectionString;
	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}	
}
