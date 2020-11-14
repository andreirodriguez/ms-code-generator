package pe.company.mscodegenerator.repository.interfaces;

import java.util.List;

import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.application.domain.Table;

public interface TableRepositoryInt
{
	List<Table> getSearch(ConnectionDb connection);
}
