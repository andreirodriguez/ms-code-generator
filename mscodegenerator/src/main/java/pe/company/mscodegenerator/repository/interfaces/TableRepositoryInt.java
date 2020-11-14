package pe.company.mscodegenerator.repository.interfaces;

import java.util.List;

import pe.company.mscodegenerator.application.domain.Table;

public interface TableRepositoryInt
{
	List<Table> getSearch(String dbType,String connectionString);
}
