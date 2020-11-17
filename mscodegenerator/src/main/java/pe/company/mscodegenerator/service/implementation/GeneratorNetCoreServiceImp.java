package pe.company.mscodegenerator.service.implementation;

import pe.company.mscodegenerator.application.domain.Generator;

import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.Field;
import pe.company.mscodegenerator.service.interfaces.GeneratorNetCoreServiceInt;

@Service
public class GeneratorNetCoreServiceImp implements GeneratorNetCoreServiceInt 
{

	@Override
	public Boolean setFiles(Generator generator) 
	{
		this.setViewModel(generator);
		
		this.setMapper(generator);
		
		this.setIQuery(generator);

		return true;
	}

	
	private void setViewModel(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classViewModel = generator.getEntity() + "ViewModel";
    	String classRequest = generator.getEntity() + "Request";    	
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classViewModel + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using System;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Queries.ViewModels" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classViewModel + separator);
    	notepad.append("	{" + separator);
    	
    	for(Field c:generator.getFields())
    		notepad.append("		public " + this.getPropertyNetCore(c) + " { get; set; }" + separator);
    	
    	notepad.append("	}" + separator);
    	notepad.append(separator);
    	notepad.append("	public class " + classRequest + " : PaginationRequest" + separator);
    	notepad.append("	{" + separator);
		notepad.append("		public " + this.getPropertyNetCore(primaryKey) + " { get; set; }" + separator);
    	notepad.append("	}" + separator);    	
    	notepad.append("}");    	
    	
		generator.getNotepads().put(file, notepad);
	}
	
	private void setMapper(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classViewModel = generator.getEntity() + "ViewModel";
    	String classMapper = generator.getEntity() + "Mapper";
    	String classIMapper = "I" + generator.getEntity() + "Mapper";
    	
    	String file = classMapper + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using " + generator.getProject() + ".Application.Queries.ViewModels;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Queries.Mappers" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public interface " + classIMapper + separator);
    	notepad.append("	{" + separator);
		notepad.append("		" + classViewModel + " MapTo" + classViewModel + "(dynamic r);" + separator);
    	notepad.append("	}" + separator);
    	notepad.append(separator);
    	notepad.append("	public class " + classMapper + " : " + classIMapper +  separator);
    	notepad.append("	{" + separator);
		notepad.append("		public " + classViewModel + " MapTo" + classViewModel + "(dynamic r)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			" + classViewModel + " o" + " = new " + classViewModel + "();"  + separator);
		notepad.append(separator);
		
		for(Field c:generator.getFields())
			notepad.append("			o." + c.getName() + " =  r." + c.getNameDb() + ";" + separator);
		
		notepad.append(separator);
		notepad.append("			return o;" + separator);
		notepad.append("		}" + separator);
    	notepad.append("	}" + separator);    	
    	notepad.append("}");
     	
		generator.getNotepads().put(file, notepad);
	}	
	
	private void setIQuery(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classViewModel = generator.getEntity() + "ViewModel";
    	String classRequest = generator.getEntity() + "Request";
    	String classIQuery = "I" + generator.getEntity() + "Query";     
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classIQuery + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using System.Collections.Generic;" + separator);
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("using " + generator.getProject() + ".Application.Queries.ViewModels;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Queries.Interfaces" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public interface " + classIQuery + separator);
    	notepad.append("	{" + separator);
		notepad.append("		Task<" + classViewModel + "> GetById(" + primaryKey.getDataType() + " " + primaryKey.getName() + ");" + separator);
		notepad.append(separator);
		notepad.append("		Task<IEnumerable<" + classViewModel + ">> GetBySearch(" + classRequest + " request);" + separator);
		notepad.append(separator);
		notepad.append("		Task<PaginationViewModel<" + classViewModel + ">> GetByFindAll(" + classRequest + " request);" + separator);
    	notepad.append("	}" + separator);
    	notepad.append("}");    	
     	
		generator.getNotepads().put(file, notepad);
	}		
	
    private String getPropertyNetCore(Field field) 
    {
    	String nulleable = "";
    	
    	if(field.getIsNull())
    		if(!field.getDataType().toLowerCase().equals("string"))
    			nulleable = "?";
    	
		return field.getDataType() + nulleable +  " " + field.getName();
    }	
}