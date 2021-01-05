package pe.company.mscodegenerator.service.interfaces;

import java.util.List;

import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.application.domain.Column;

public interface ColumnServiceInt 
{
	List<Column> getSelect(ConnectionDb connection,String table,String programmingLanguage);
}
