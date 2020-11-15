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

@Service
public class GeneratorServiceImp implements GeneratorServiceInt 
{
	@Autowired
	private GeneratorRepositoryInt generatorRepository;
	
	@Override
	public Boolean setGenerate(Generator generator) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");		
		String directoryPath = "./files/" + dateFormat.format(new Date(System.currentTimeMillis())); 		
		
		SupportFile.CreateDirectory(directoryPath);

		generator.setPath(directoryPath);
		
		this.generateSqlServer(generator);
		
		return true;
	}

	private void generateSqlServer(Generator generator)
	{
    	String file = null;
    	String procedure = null;
    	StringBuilder notepad = null;
    	String separator = System.getProperty("line.separator");
    	
    	//Search 
    	notepad = new StringBuilder();
		
    	procedure = generator.getTable() + "_search";
    	file = procedure + ".sql";
    	
    	notepad.append("Create Procedure " + procedure + separator);
    	
    	generatorRepository.setFiles(generator, file, notepad);
	}
}
