package pe.company.mscodegenerator.application.mapper;

import pe.company.mscodegenerator.api.request.FieldRequest;
import pe.company.mscodegenerator.api.request.GeneratorRequest;
import pe.company.mscodegenerator.application.domain.Generator;

public class GeneratorMapper 
{
	public static Generator MapperFromGeneratorRequest(GeneratorRequest i) 
	{
		Generator o = new Generator();
		
		o.setProject(i.getProject());
		o.setDbType(i.getDbType());
		o.setTable(i.getTable());
		o.setEntity(i.getEntity());
		o.setProgrammingLanguage(i.getProgrammingLanguage());
		
		for(FieldRequest f:i.getFields())
			o.getFields().add(FieldMapper.MapperFromFieldRequest(f));
		
		return o;
	}
}
