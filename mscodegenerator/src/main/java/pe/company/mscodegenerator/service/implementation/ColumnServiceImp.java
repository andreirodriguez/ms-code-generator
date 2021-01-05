package pe.company.mscodegenerator.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.cross.variables.DbType;
import pe.company.mscodegenerator.cross.variables.ProgrammingLanguage;
import pe.company.mscodegenerator.application.domain.Column;
import pe.company.mscodegenerator.repository.interfaces.ColumnRepositoryInt;
import pe.company.mscodegenerator.service.interfaces.ColumnServiceInt;

@Service
public class ColumnServiceImp implements ColumnServiceInt 
{
	@Autowired
	private ColumnRepositoryInt columnRepository;
	
	@Override
	public List<Column> getSelect(ConnectionDb connection,String table,String programmingLanguage)
	{
		List<Column> l = columnRepository.getSearch(connection,table);
		
		this.setDataTypeEntity(connection,programmingLanguage,l);
		
		return l;
	}	
	
	private void setDataTypeEntity(ConnectionDb connection,String programmingLanguage,List<Column> l)
	{
		for(Column c:l)
			switch(connection.getDbType())
			{
				case DbType.SqlServer:
					switch(programmingLanguage)
					{
						case ProgrammingLanguage.NetCore:
							c.setDataTypeEntity(this.getDataType_SqlServer_NetCore(c.getDataType()));
							break;
					}					
					break;
			}
	}
	
	private String getDataType_SqlServer_NetCore(String dataTypeDb)
	{
    	String dataType = "string";
    	
    	dataTypeDb = dataTypeDb.trim().toUpperCase();
    	
    	if(dataTypeDb.indexOf("INT")>=0)
    		dataType = "int";
    	else if(dataTypeDb.indexOf("CHAR")>=0)
    		dataType = "string";
    	else if(dataTypeDb.indexOf("DATE")>=0)
    		dataType = "DateTime";    	
    	else if(dataTypeDb.indexOf("DECIMAL")>=0)
    		dataType = "decimal";    	
    	else if(dataTypeDb.indexOf("DOUBLE")>=0)
    		dataType = "double";    	    	
    	else if(dataTypeDb.indexOf("BIT")>=0)
    		dataType = "bool";      	
    	
    	return dataType;		
	}
}
