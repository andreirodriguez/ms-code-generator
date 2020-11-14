package pe.company.mscodegenerator.cross.utils;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

public class SupportDatabase 
{
	public static DataSource getDataSourceByConnectionString(String dbType,String connectionString)
	{
		DriverConnection connection = new DriverConnection(dbType,connectionString);
		
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName(connection.getDriver());
	    dataSourceBuilder.url("jdbc:" + connection.getServer() + ";" + connection.getDataBase());
	    dataSourceBuilder.username(connection.getUserName());
	    dataSourceBuilder.password(connection.getPassword());
	    
	    return dataSourceBuilder.build();
	}
}
