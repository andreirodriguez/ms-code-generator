package pe.company.mscodegenerator.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.repository.interfaces.TableRepositoryInt;
import pe.company.mscodegenerator.service.interfaces.TableServiceInt;
import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.application.domain.Table;

@Service
public class TableServiceImp implements TableServiceInt 
{
	@Autowired
	private TableRepositoryInt tableRepository;
	
	@Override
	public List<Table> getSelect(ConnectionDb connection)
	{
		return tableRepository.getSearch(connection);		
	}		
}
