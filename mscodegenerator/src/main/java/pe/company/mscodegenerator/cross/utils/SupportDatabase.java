package pe.company.mscodegenerator.cross.utils;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

import pe.company.mscodegenerator.cross.variables.DbType;

public class SupportDatabase 
{
	public static DataSource getDataSourceByConnectionString(String dbType,String server,String dataBase,String userName,String password)
	{	
		//Set Driver
		String driver = null;
		
		switch(dbType)
		{
			case DbType.SqlServer:
				driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				break;
		}			
		
		//Set Url
		String url = null;
		
		switch(dbType)
		{
			case DbType.SqlServer:
				url = "jdbc:sqlserver://" + server + ";" + "databaseName=" + dataBase;
				break;
		}		
		
		
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName(driver);
	    dataSourceBuilder.url(url);
	    dataSourceBuilder.username(userName);
	    dataSourceBuilder.password(password);
	    
	    return dataSourceBuilder.build();
	}
	
}
