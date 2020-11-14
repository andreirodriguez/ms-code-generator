package pe.company.mscodegenerator.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.application.domain.Column;
import pe.company.mscodegenerator.repository.interfaces.ColumnRepositoryInt;
import pe.company.mscodegenerator.service.interfaces.ColumnServiceInt;

@Service
public class ColumnServiceImp implements ColumnServiceInt 
{
	@Autowired
	private ColumnRepositoryInt columnRepository;
	
	@Override
	public List<Column> getSelect(ConnectionDb connection,String table)
	{
		return columnRepository.getSearch(connection,table);		
	}	
}
