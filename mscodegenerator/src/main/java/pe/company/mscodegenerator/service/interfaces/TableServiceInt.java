package pe.company.mscodegenerator.service.interfaces;

import java.util.List;

import pe.company.mscodegenerator.application.domain.Table;

public interface TableServiceInt 
{
	List<Table> getSelect(String dbType,String connectionString);
}
