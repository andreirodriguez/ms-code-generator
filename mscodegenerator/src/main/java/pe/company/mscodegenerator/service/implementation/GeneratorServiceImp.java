package pe.company.mscodegenerator.service.implementation;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.Generator;
import pe.company.mscodegenerator.cross.utils.SupportFile;
import pe.company.mscodegenerator.repository.interfaces.GeneratorRepositoryInt;
import pe.company.mscodegenerator.repository.interfaces.TableRepositoryInt;
import pe.company.mscodegenerator.service.interfaces.GeneratorServiceInt;
import pe.company.mscodegenerator.service.interfaces.GeneratorSqlServerServiceInt;

@Service
public class GeneratorServiceImp implements GeneratorServiceInt 
{
	@Autowired
	private GeneratorRepositoryInt generatorRepository;
	
	@Autowired
	private GeneratorSqlServerServiceInt generatorSqlServerService;	
	
	@Override
	public Boolean setGenerate(Generator generator) 
	{
		//Initialize
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");		
		String directoryPath = "./files/" + dateFormat.format(new Date(System.currentTimeMillis())); 		
		
		SupportFile.CreateDirectory(directoryPath);

		generator.setPath(directoryPath);
		
		//Sql Server
		generatorSqlServerService.setFiles(generator);
		
		
		//Save		
		return generatorRepository.setFiles(generator);
	}
}
