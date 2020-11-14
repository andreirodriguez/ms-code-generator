package pe.company.mscodegenerator.cross.utils;

import pe.company.mscodegenerator.cross.variables.DbType;

public class DriverConnection 
{
	private String driver;
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	
	private String server;
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}	
	
	private String dataBase;
	public String getDataBase() {
		return dataBase;
	}
	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}	

	private String userName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}		
	
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}			
	
	public DriverConnection(String dbType,String connectionString)
	{	
		//Set Driver
		switch(dbType)
		{
			case DbType.SqlServer:
				this.driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				break;
		}		
		
		String[] connection = connectionString.split(";");
		
		this.server = connection[0];
		this.dataBase = connection[1];
		this.userName = connection[2].split("=")[1];
		this.password = connection[3].split("=")[1];
	}
}
