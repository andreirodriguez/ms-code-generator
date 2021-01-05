package pe.company.mscodegenerator.application.mapper;

import pe.company.mscodegenerator.api.request.ConnectionDbRequest;
import pe.company.mscodegenerator.application.domain.ConnectionDb;

public class ConnectionDbMapper 
{
	public static ConnectionDb MapperFromConnectionDbRequest(ConnectionDbRequest i) 
	{
		ConnectionDb o = new ConnectionDb();
		
		o.setDbType(i.getDbType());
		o.setServer(i.getServer());
		o.setDataBase(i.getDataBase());
		o.setUserName(i.getUserName());
		o.setPassword(i.getPassword());
		
		return o;
	}
}
