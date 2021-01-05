package pe.company.mscodegenerator.repository.interfaces;

import java.util.List;

import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.application.domain.Column;

public interface ColumnRepositoryInt 
{
	List<Column> getSearch(ConnectionDb connection,String table);

}
