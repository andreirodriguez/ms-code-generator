package pe.company.mscodegenerator.repository.implementation;

import org.springframework.stereotype.Repository;

import pe.company.mscodegenerator.application.domain.Generator;
import pe.company.mscodegenerator.cross.utils.SupportFile;
import pe.company.mscodegenerator.repository.interfaces.GeneratorRepositoryInt;

@Repository
public class GeneratorRepositoryImp implements GeneratorRepositoryInt
{
	@Override
	public Boolean setFiles(Generator generator,String file,StringBuilder notepad)
	{
		String pathFile = SupportFile.CombinePath(generator.getPath(),file);
		
		Boolean createFile = SupportFile.WriteFilePlainText(pathFile, notepad);
		
		if(createFile) generator.getFiles().put(file, pathFile);
		
		return createFile;
	}
	
}
