package pe.company.mscodegenerator.service.implementation;

import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.Generator;
import pe.company.mscodegenerator.application.domain.Field;
import pe.company.mscodegenerator.service.interfaces.GeneratorNetCoreServiceInt;
import pe.company.mscodegenerator.cross.utils.ConvertFormat;

@Service
public class GeneratorNetCoreServiceImp implements GeneratorNetCoreServiceInt 
{

	@Override
	public Boolean setFiles(Generator generator) 
	{
		this.setViewModel(generator);
		
		this.setMapper(generator);
		
		this.setIQuery(generator);
		
		this.setQuery(generator);
		
		this.setCreateCommand(generator);
		
		this.setCreateCommandHandler(generator);
		
		this.setUpdateCommand(generator);
		
		this.setUpdateCommandHandler(generator);
		
		this.setDomain(generator);
		
		this.setIRepository(generator);
		
		this.setRepository(generator);
		
		this.setController(generator);

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

	private void setQuery(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classViewModel = generator.getEntity() + "ViewModel";
    	String classRequest = generator.getEntity() + "Request";
    	String classMapper = generator.getEntity() + "Mapper";
    	String classIMapper = "I" + generator.getEntity() + "Mapper";
    	String classIQuery = "I" + generator.getEntity() + "Query";
    	String classQuery = generator.getEntity() + "Query";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classQuery + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
       	notepad.append("using System;" + separator);
    	notepad.append("using System.Collections.Generic;" + separator);
    	notepad.append("using System.Linq;" + separator);
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("using " + generator.getProject() + ".Application.Utility;" + separator);
    	notepad.append("using " + generator.getProject() + ".Application.Queries.Interfaces;" + separator);
    	notepad.append("using " + generator.getProject() + ".Application.Queries.ViewModels;" + separator);
    	notepad.append("using " + generator.getProject() + ".Application.Queries.Mappers;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Queries.Implementations" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classQuery + " : " + classIQuery + separator);
    	notepad.append("	{" + separator);
    	notepad.append("		private readonly IGenericQuery _iGenericQuery;" + separator);
    	notepad.append("		private readonly " + classIMapper + " _i" + classMapper + ";" + separator);
    	notepad.append(separator);
    	notepad.append("		public " + classQuery + "(IGenericQuery iGenericQuery, " + classIMapper + " i" + classMapper + ")" + separator);
    	notepad.append("		{" + separator);
		notepad.append("			_iGenericQuery = iGenericQuery ?? throw new ArgumentNullException(nameof(iGenericQuery));" + separator);
		notepad.append("			_i" + classMapper + " = i" + classMapper + " ?? throw new ArgumentNullException(nameof(i" + classMapper + "));" + separator);
    	notepad.append("		}" + separator);    	
    	notepad.append(separator);
    	notepad.append("		public async Task<" + classViewModel + "> GetById(" + primaryKey.getDataType() + " " + primaryKey.getName() + ")" + separator);
    	notepad.append("		{" + separator);
		notepad.append("			var parameters = new Dictionary<string, object>" + separator);
		notepad.append("			{" + separator);
		notepad.append("				{\"" + primaryKey.getNameDb() + "\", " + primaryKey.getName() + "}" + separator);
		notepad.append("			};" + separator);
		notepad.append(separator);
		notepad.append("			var result = await _iGenericQuery.Search(@\"" + generator.getTable() + "_search\", ConvertTo.Xml(parameters));" + separator);
		notepad.append(separator);
		notepad.append("			return (result != null) ? _i" + classMapper + ".MapTo" + classViewModel + "(result) : null;" + separator);		
		notepad.append("		}" + separator);
    	notepad.append(separator);
    	notepad.append("		public async Task<IEnumerable<" + classViewModel + ">> GetBySearch(" + classRequest + " request)"+ separator);
    	notepad.append("		{" + separator);
		notepad.append("			var parameters = new Dictionary<string, object>" + separator);
		notepad.append("			{" + separator);
		notepad.append("				{\"" + primaryKey.getNameDb() + "\", request." + primaryKey.getName() + "}" + separator);
		notepad.append("			};" + separator);
		notepad.append(separator);
		notepad.append("			var result = await _iGenericQuery.Search(@\"" + generator.getTable() + "_search\", ConvertTo.Xml(parameters), request.pagination);" + separator);
		notepad.append(separator);
		notepad.append("			var items = result.Select(item => (" + classViewModel + ")_i" + classMapper + ".MapTo" + classViewModel + "(item));" + separator);		
		notepad.append(separator);
		notepad.append("			return items;" + separator);		
		notepad.append("		}" + separator);		
    	notepad.append(separator);
    	notepad.append("		public async Task<PaginationViewModel<" + classViewModel + ">> GetByFindAll(" + classRequest + " request)" + separator);
    	notepad.append("		{" + separator);
		notepad.append("			var parameters = new Dictionary<string, object>" + separator);
		notepad.append("			{" + separator);
		notepad.append("				{\"" + primaryKey.getNameDb() + "\", request." + primaryKey.getName() + "}" + separator);
		notepad.append("			};" + separator);
		notepad.append(separator);
		notepad.append("			var result = await _iGenericQuery.FindAll(@\"" + generator.getTable() + "_find_all\", ConvertTo.Xml(parameters), request.pagination);" + separator);
		notepad.append(separator);
		notepad.append("			var items = result.Select(item => (" + classViewModel + ")_i" + classMapper + ".MapTo" + classViewModel + "(item));" + separator);		
		notepad.append(separator);
		notepad.append("			return new PaginationViewModel<" + classViewModel + ">(request.pagination, items);" + separator);		
		notepad.append("		}" + separator);				
    	notepad.append("	}" + separator);
    	notepad.append("}");      	
     	
		generator.getNotepads().put(file, notepad);
	}		
	
	private void setCreateCommand(Generator generator)
	{
    	String separator = System.getProperty("line.separator");
    	
    	String classCreateCommand= "Create" + generator.getEntity() + "Command";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classCreateCommand + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using System;" + separator);
    	notepad.append(separator);
    	notepad.append("using MediatR;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Commands." + generator.getEntity() + "Command" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classCreateCommand + " : IRequest<" + primaryKey.getDataType() + ">" + separator);
    	notepad.append("	{" + separator);
    	
    	for(Field c:generator.getFields())
    		if(c.getId() != primaryKey.getId())
    			notepad.append("		public " + this.getPropertyNetCore(c) + " { get; set; }" + separator);
    	
    	notepad.append("	}" + separator);
    	notepad.append("}"); 	
     	
		generator.getNotepads().put(file, notepad);
	}		
	
	private void setCreateCommandHandler(Generator generator)
	{
    	String separator = System.getProperty("line.separator");
    	String lineCode;

    	String classIRepository = "I" + generator.getEntity() + "Repository";
    	String classCreateCommand= "Create" + generator.getEntity() + "Command";
    	String classCreateCommandHandler= classCreateCommand + "Handler";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classCreateCommandHandler + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();    
    	
    	notepad.append("using MediatR;" + separator);
    	notepad.append(separator);
    	notepad.append("using System.Threading;" + separator);	
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("using " + generator.getProject() + ".Domain.Aggregates." + generator.getEntity() + "Aggregate;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Commands." + generator.getEntity() + "Command" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classCreateCommandHandler + " : IRequestHandler<" + classCreateCommand + ", " + primaryKey.getDataType() + ">" + separator);
    	notepad.append("	{" + separator);
    	notepad.append("		readonly " + classIRepository + " _" + ConvertFormat.getLowerCamelCase(classIRepository) + ";" + separator);
    	notepad.append(separator);
    	notepad.append("		public " + classCreateCommandHandler + "(" + classIRepository + " " + ConvertFormat.getLowerCamelCase(classIRepository) + ")" + separator);
    	notepad.append("		{" + separator);
    	notepad.append("			_" + ConvertFormat.getLowerCamelCase(classIRepository) + " = " +  ConvertFormat.getLowerCamelCase(classIRepository) + ";" + separator);
    	notepad.append("		}" + separator);
    	notepad.append(separator);
    	notepad.append("		public async Task<" + primaryKey.getDataType() + "> Handle(" + classCreateCommand + " request, CancellationToken cancellationToken)" + separator);
    	notepad.append("		{" + separator);
    	
    	lineCode = "";
    	for(Field c:generator.getFields())
    		if(c.getId()!=primaryKey.getId())
    			lineCode += ", request." + c.getName(); 
    	
    	notepad.append("			" + generator.getEntity() + " " + ConvertFormat.getLowerCamelCase(generator.getEntity()) + " = new " + generator.getEntity() + "(" + lineCode.substring(1) + ");" + separator);
    	notepad.append(separator);
    	notepad.append("			var result = await _" +  ConvertFormat.getLowerCamelCase(classIRepository) + ".Register(" + ConvertFormat.getLowerCamelCase(generator.getEntity()) + ");" + separator);
    	notepad.append(separator);
    	notepad.append("			return result;" + separator);
    	notepad.append("		}" + separator);    	
    	notepad.append("	}" + separator);
    	notepad.append("}");
     	
		generator.getNotepads().put(file, notepad);
	}			

	private void setUpdateCommand(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classUpdateCommand= "Update" + generator.getEntity() + "Command";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classUpdateCommand + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using System;" + separator);
    	notepad.append(separator);
    	notepad.append("using MediatR;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Commands." + generator.getEntity() + "Command" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classUpdateCommand + " : IRequest<" + primaryKey.getDataType() + ">" + separator);
    	notepad.append("	{" + separator);
    	
    	for(Field c:generator.getFields())
			notepad.append("		public " + this.getPropertyNetCore(c) + " { get; set; }" + separator);
    	
    	notepad.append("	}" + separator);
    	notepad.append("}");
     	
		generator.getNotepads().put(file, notepad);
	}	
	
	private void setUpdateCommandHandler(Generator generator)
	{
    	String separator = System.getProperty("line.separator");
    	String lineCode;

    	String classIRepository = "I" + generator.getEntity() + "Repository";
    	String classUpdateCommand= "Update" + generator.getEntity() + "Command";
    	String classUpdateCommandHandler= classUpdateCommand + "Handler";    
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classUpdateCommandHandler + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();    
    	
    	notepad.append("using MediatR;" + separator);
    	notepad.append(separator);
    	notepad.append("using System.Threading;" + separator);	
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("using " + generator.getProject() + ".Domain.Aggregates." + generator.getEntity() + "Aggregate;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Application.Commands." + generator.getEntity() + "Command" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classUpdateCommandHandler + " : IRequestHandler<" + classUpdateCommand + ", " + primaryKey.getDataType() + ">" + separator);
    	notepad.append("	{" + separator);
    	notepad.append("		readonly " + classIRepository + " _" + ConvertFormat.getLowerCamelCase(classIRepository) + ";" + separator);
    	notepad.append(separator);
    	notepad.append("		public " + classUpdateCommandHandler + "(" + classIRepository + " " + ConvertFormat.getLowerCamelCase(classIRepository) + ")" + separator);
    	notepad.append("		{" + separator);
    	notepad.append("			_" + ConvertFormat.getLowerCamelCase(classIRepository) + " = " +  ConvertFormat.getLowerCamelCase(classIRepository) + ";" + separator);
    	notepad.append("		}" + separator);
    	notepad.append(separator);
    	notepad.append("		public async Task<" + primaryKey.getDataType() + "> Handle(" + classUpdateCommand + " request, CancellationToken cancellationToken)" + separator);
    	notepad.append("		{" + separator);
    	
    	lineCode = "";
    	for(Field c:generator.getFields())
    		lineCode += ", request." + c.getName(); 
    	
    	notepad.append("			" + generator.getEntity() + " " + ConvertFormat.getLowerCamelCase(generator.getEntity()) + " = new " + generator.getEntity() + "(" + lineCode.substring(1) + ");" + separator);
    	notepad.append(separator);
    	notepad.append("			var result = await _" +  ConvertFormat.getLowerCamelCase(classIRepository) + ".Register(" + ConvertFormat.getLowerCamelCase(generator.getEntity()) + ");" + separator);
    	notepad.append(separator);
    	notepad.append("			return result;" + separator);
    	notepad.append("		}" + separator);    	
    	notepad.append("	}" + separator);
    	notepad.append("}");
     	
		generator.getNotepads().put(file, notepad);
	}		
	
	private void setDomain(Generator generator)
	{
    	String separator = System.getProperty("line.separator");
    	String lineCode;
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = generator.getEntity() + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using System;" + separator);
    	notepad.append(separator);
    	notepad.append("using " + generator.getProject() + ".Domain.Core;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Domain.Aggregates." + generator.getEntity() + "Aggregate" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + generator.getEntity() + " : Entity" + separator);
    	notepad.append("	{" + separator);
    	
    	for(Field c:generator.getFields())
    	{
    		if(c.getId()==primaryKey.getId())
    			notepad.append("		public " + this.getPropertyNetCore(c) + " { get; set; }" + separator);
    		else
    			notepad.append("		public " + this.getPropertyNetCore(c) + " { get; }" + separator);
    	}
    	
    	notepad.append(separator);
    	notepad.append("		public " + generator.getEntity() + "()" + separator);
    	notepad.append("		{" + separator);
    	notepad.append("		}" + separator);
    	
    	lineCode = "";
    	for(Field c:generator.getFields())
    		if(c.getId()!=primaryKey.getId())
    			lineCode += ", " + this.getPropertyNetCore(c);
    	
    	notepad.append(separator);    	
    	notepad.append("		public " + generator.getEntity() + "(" + lineCode.substring(1) + ")" + separator);
    	notepad.append("		{" + separator);
    	
    	for(Field c:generator.getFields())
    		if(c.getId()!=primaryKey.getId()) 	
				notepad.append("			this." + c.getName() + " = " + c.getName() + ";" + separator);
    	
    	notepad.append("		}" + separator);    	

    	lineCode = "";
    	for(Field c:generator.getFields())
    		lineCode += ", " + this.getPropertyNetCore(c);
    	
    	notepad.append(separator);    	
    	notepad.append("		public " + generator.getEntity() + "(" + lineCode.substring(1) + ")" + separator);
    	notepad.append("		{" + separator);
    	
    	for(Field c:generator.getFields())  	
			notepad.append("			this." + c.getName() + " = " + c.getName() + ";" + separator);
    	
    	notepad.append("		}" + separator);    	
    	notepad.append("	}" + separator);
    	notepad.append("}");
     	
		generator.getNotepads().put(file, notepad);
	}	

	private void setIRepository(Generator generator)
	{
    	String separator = System.getProperty("line.separator");
    	
    	String classIRepository = "I" + generator.getEntity() + "Repository";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classIRepository + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();    
    	
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Domain.Aggregates." + generator.getEntity() + "Aggregate" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public interface " + classIRepository + separator);
    	notepad.append("	{" + separator);
		notepad.append("		Task<" + primaryKey.getDataType() + "> Register(" + generator.getEntity() + " " + ConvertFormat.getLowerCamelCase(generator.getEntity()) + ");" + separator);
    	notepad.append("	}" + separator);
    	notepad.append("}");    		
     	
		generator.getNotepads().put(file, notepad);
	}		
	
	private void setRepository(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classIRepository = "I" + generator.getEntity() + "Repository";
    	String classRepository = generator.getEntity() + "Repository";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classRepository + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();     
    	
    	notepad.append("using System;" + separator);
    	notepad.append("using System.Data;" + separator);
    	notepad.append("using System.Data.SqlClient;" + separator);
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("using Dapper;" + separator);
    	notepad.append(separator);
    	notepad.append("using " + generator.getProject() +  ".Domain.Exceptions;" + separator);
    	notepad.append("using " + generator.getProject() +  ".Domain.Aggregates." + generator.getEntity() + "Aggregate;" + separator);
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".Repository" + separator);
    	notepad.append("{" + separator);
    	notepad.append("	public class " + classRepository + " : " + classIRepository + separator);
    	notepad.append("	{" + separator);
		notepad.append("		readonly string _connectionString = string.Empty;" + separator);
		notepad.append(separator);
		notepad.append("		public " + classRepository + "(string connectionString)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			_connectionString = connectionString;" + separator);
		notepad.append("		}" + separator);
		notepad.append(separator);
		notepad.append("		public async Task<" + primaryKey.getDataType() + "> Register(" + generator.getEntity() + " " + ConvertFormat.getLowerCamelCase(generator.getEntity()) + ")" + separator);
		notepad.append("		{" + separator);
		notepad.append("			using (var connection = new SqlConnection(_connectionString))" + separator);
		notepad.append("			{" + separator);
		notepad.append("				await connection.OpenAsync();" + separator);
		notepad.append(separator);
		notepad.append("				try" + separator);
		notepad.append("				{" + separator);
		notepad.append("					var parameters = new DynamicParameters();" + separator);
		notepad.append(separator);
		
		for(Field c:generator.getFields())
			notepad.append("					" + this.getDynamicParameter(generator, c, primaryKey.getName().equals(c.getName())) + separator);
		
		notepad.append(separator);
		
		notepad.append("					var result = await connection.ExecuteAsync(@\"" + generator.getTable() + "_insert_update\", parameters, commandType: CommandType.StoredProcedure);" + separator);
		notepad.append(separator);
		notepad.append("					" + ConvertFormat.getLowerCamelCase(generator.getEntity()) + "." + primaryKey.getName() + " = parameters.Get<" + primaryKey.getDataType() + ">(\"@po" + primaryKey.getDataType().substring(0,1) + "_" + primaryKey.getNameDb() + "\");" + separator);
		notepad.append(separator);
		notepad.append("					return " + ConvertFormat.getLowerCamelCase(generator.getEntity()) + "." + primaryKey.getName() +  ";" + separator);
		notepad.append("				}" + separator);
		notepad.append("				catch (Exception ex)" + separator);
		notepad.append("				{" + separator);
		notepad.append("					throw new " + generator.getProject() + "BaseException(ex.Message);" + separator);
		notepad.append("				}" + separator);
		notepad.append("			}" + separator);
		notepad.append("		}" + separator);		
    	notepad.append("	}" + separator);
    	notepad.append("}");   		
     	
		generator.getNotepads().put(file, notepad);
	}			

	private void setController(Generator generator)
	{
    	String separator = System.getProperty("line.separator");

    	String classViewModel = generator.getEntity() + "ViewModel";
    	String classRequest = generator.getEntity() + "Request";
    	String classIQuery = "I" + generator.getEntity() + "Query";
    	String classController = generator.getEntity() + "Controller";
    	String classCommand= generator.getEntity() + "Command";
    	String classCreateCommand= "Create" + generator.getEntity() + "Command";
    	String classUpdateCommand= "Update" + generator.getEntity() + "Command";
    	
    	Field primaryKey = generator.getFields().get(0);
    	String file = classController + ".cs";
    	
    	StringBuilder notepad = new StringBuilder();   
    	
    	notepad.append("using System;" + separator);
    	notepad.append("using System.Collections.Generic;" + separator);
    	notepad.append("using System.Net;" + separator);
    	notepad.append("using System.Threading.Tasks;" + separator);
    	notepad.append(separator);
    	notepad.append("using MediatR;" + separator);
    	notepad.append("using Microsoft.AspNetCore.Authorization;" + separator);
    	notepad.append("using Microsoft.AspNetCore.Mvc;" + separator);
    	notepad.append(separator);    	
    	notepad.append("using " + generator.getProject() + ".API.Services;" + separator);
    	notepad.append("using " + generator.getProject() + ".Application.Queries.Interfaces;" + separator);
    	notepad.append("using " + generator.getProject() + ".Application.Queries.ViewModels;" + separator);
    	notepad.append("using " + generator.getProject() + ".Application.Commands." + classCommand + ";" + separator);    	
    	notepad.append(separator);
    	notepad.append("namespace " + generator.getProject() + ".API.Controllers" + separator);
    	notepad.append("{" + separator);
		notepad.append("	[Authorize]" + separator);
    	notepad.append("	[Route(\"" + ConvertFormat.getLowerCamelCase(generator.getEntity()) + "s\")]" + separator);
    	notepad.append("	[ApiController]" + separator);
    	notepad.append("	public class " + classController + " : ControllerBase" + separator);
    	notepad.append("	{" + separator);
		notepad.append("		readonly " + classIQuery + " _" + ConvertFormat.getLowerCamelCase(classIQuery) + ";" + separator);
		notepad.append("		readonly ISomeService _someServices;" + separator);
		notepad.append("		readonly IMediator _mediator;" + separator);
		notepad.append(separator);
		notepad.append("		public " + classController + "(" + classIQuery + " " + ConvertFormat.getLowerCamelCase(classIQuery) + ", ISomeService someServices,IMediator mediator)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			_" + ConvertFormat.getLowerCamelCase(classIQuery) + " = " + ConvertFormat.getLowerCamelCase(classIQuery) + " ?? throw new ArgumentNullException(nameof(" + ConvertFormat.getLowerCamelCase(classIQuery) + "));" + separator);
		notepad.append("			_someServices = someServices ?? throw new ArgumentNullException(nameof(someServices));" + separator);
		notepad.append("			_mediator = mediator ?? throw new ArgumentNullException(nameof(mediator));" + separator);
		notepad.append("		}" + separator);
		notepad.append(separator);
		notepad.append("		[HttpGet]" + separator);
		notepad.append("		[Route(\"{" + primaryKey.getName() + "}\")]" + separator);
		notepad.append("		[ProducesResponseType(typeof(" + classViewModel + "), (int)HttpStatusCode.OK)]" + separator);
		notepad.append("		[ProducesResponseType((int)HttpStatusCode.NotFound)]" + separator);
		notepad.append("		public async Task<IActionResult> GetById(" + primaryKey.getDataType() + " " + primaryKey.getName() + ")" + separator);
		notepad.append("		{" + separator);
		notepad.append("			var result = await _" + ConvertFormat.getLowerCamelCase(classIQuery) + ".GetById(" + primaryKey.getName() + ");" + separator);
		notepad.append(separator);
		notepad.append("			if (result != null)" + separator);
		notepad.append("				return Ok(result);" + separator);
		notepad.append("			else" + separator);
		notepad.append("				return NotFound();" + separator);
		notepad.append("		}" + separator);
		notepad.append(separator);
		notepad.append("		[HttpGet]" + separator);
		notepad.append("		[Route(\"search\")]" + separator);
		notepad.append("		[ProducesResponseType(typeof(IEnumerable<" + classViewModel + ">), (int)HttpStatusCode.OK)]" + separator);
		notepad.append("		public async Task<IActionResult> GetBySearch([FromQuery] " + classRequest + " request)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			var result = await _" + ConvertFormat.getLowerCamelCase(classIQuery) + ".GetBySearch(request);" + separator);
		notepad.append(separator);
		notepad.append("			return Ok(result);" + separator);
		notepad.append("		}" + separator);		
		notepad.append(separator);
		notepad.append("		[HttpGet]" + separator);
		notepad.append("		[Route(\"find-all\")]" + separator);
		notepad.append("		[ProducesResponseType(typeof(PaginationViewModel<" + classViewModel + ">), (int)HttpStatusCode.OK)]" + separator);
		notepad.append("		public async Task<IActionResult> GetByFindAll([FromQuery] " + classRequest + " request)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			var result = await _" + ConvertFormat.getLowerCamelCase(classIQuery) + ".GetByFindAll(request);" + separator);
		notepad.append(separator);
		notepad.append("			return Ok(result);" + separator);
		notepad.append("		}" + separator);				
		notepad.append(separator);
		notepad.append("		[HttpPost]" + separator);
		notepad.append("		[ProducesResponseType((int)HttpStatusCode.Created)]" + separator);
		notepad.append("		[ProducesResponseType((int)HttpStatusCode.BadRequest)]" + separator);
		notepad.append("		public async Task<IActionResult> Create" + generator.getEntity() + "(" + classCreateCommand + " command)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			var result = await _mediator.Send(command);" + separator);
		notepad.append(separator);
		notepad.append("			return CreatedAtAction(nameof(Create" + generator.getEntity() + "), result);" + separator);
		notepad.append("		}" + separator);						
		notepad.append(separator);
		notepad.append("		[HttpPut]" + separator);
		notepad.append("		[ProducesResponseType((int)HttpStatusCode.OK)]" + separator);
		notepad.append("		[ProducesResponseType((int)HttpStatusCode.BadRequest)]" + separator);
		notepad.append("		public async Task<IActionResult> Update" + generator.getEntity() + "(" + classUpdateCommand + " command)" + separator);
		notepad.append("		{" + separator);
		notepad.append("			var result = await _mediator.Send(command);" + separator);
		notepad.append(separator);
		notepad.append("			return Ok(result);" + separator);
		notepad.append("		}" + separator);								
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
    
    private String getDynamicParameter(Generator generador,Field field,Boolean inputOutput)
    {
    	String dbType = "DbType.String";
    	
    	switch(field.getDataType())
    	{
    		case "int":
    			dbType = "DbType.Int32";
    			break;
    		case "string":
				dbType = "DbType.String";
				break;    		
    		case "decimal":
				dbType = "DbType.Decimal";
				break;    			
    		case "DateTime":
				dbType = "DbType.DateTime";
				break;    			
    		case "bool":
				dbType = "DbType.Boolean";
				break;    							
    	}
    	
    	
    	String parameter = "parameters.Add(";
    	
    	parameter += "\"@p" + (inputOutput ? "o" : "i") +  field.getDataTypeDb().substring(0,1) + "_" + field.getNameDb() + "\", ";
    	
    	parameter += ConvertFormat.getLowerCamelCase(generador.getEntity()) + "." + field.getName() + ", ";
    	
    	parameter += dbType + ", ";
    	
    	parameter += (inputOutput ? "ParameterDirection.InputOutput" : "ParameterDirection.Input") + ");";
    	
    	return parameter;
    }
    
    
}
