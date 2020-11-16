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

		return true;
	}

	
	private void setViewModel(Generator generator)
	{
    	String separator = System.getProperty("line.separator");
    	String lineCode;
    	Integer count;

    	String classViewModel = generator.getEntity() + "ViewModel";
    	String classRequest = generator.getEntity() + "Request";    	
    	
    	String prefixTable = generator.getTable().split("\\.")[1].toUpperCase().substring(0,3);
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
	
    private String getPropertyNetCore(Field field) 
    {
    	String nulleable = "";
    	
    	if(field.getIsNull())
    		if(!field.getDataType().toLowerCase().equals("string"))
    			nulleable = "?";
    	
		return field.getDataType() + nulleable +  " " + field.getName();
    }	
}
