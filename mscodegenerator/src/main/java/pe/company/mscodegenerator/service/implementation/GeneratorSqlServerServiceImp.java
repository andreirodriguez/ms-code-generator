package pe.company.mscodegenerator.service.implementation;

import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.Generator;
import pe.company.mscodegenerator.service.interfaces.GeneratorSqlServerServiceInt;

@Service
public class GeneratorSqlServerServiceImp implements GeneratorSqlServerServiceInt 
{

	@Override
	public Boolean setFiles(Generator generator) 
	{
    	String file = null;
    	String procedure = null;
    	StringBuilder notepad = null;
    	String separator = System.getProperty("line.separator");
    	
    	//Search 
    	procedure = generator.getTable() + "_search";
    	file = procedure + ".sql";    	
    	
    	notepad = new StringBuilder();
    	
    	notepad.append("Create Procedure " + procedure + separator);
    	
    	generator.getNotepads().put(file, notepad);
    	
    	return true;
	}

}
