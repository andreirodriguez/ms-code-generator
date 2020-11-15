package pe.company.mscodegenerator.service.implementation;

import org.springframework.stereotype.Service;

import pe.company.mscodegenerator.application.domain.Generator;
import pe.company.mscodegenerator.application.domain.Field;
import pe.company.mscodegenerator.service.interfaces.GeneratorSqlServerServiceInt;

@Service
public class GeneratorSqlServerServiceImp implements GeneratorSqlServerServiceInt 
{

	@Override
	public Boolean setFiles(Generator generator) 
	{
		this.setProcedureSearch(generator);
    	
    	return true;
	}
	
	private void setProcedureSearch(Generator generator) 
	{
    	String file = null;
    	String procedure = null;
    	StringBuilder notepad = null;
    	String separator = System.getProperty("line.separator");
    	String prefixTable = generator.getTable().split("\\.")[1].toUpperCase().substring(0,3);
    	String lineCode;
    	Integer count;
    	
    	//Search 
    	procedure = generator.getTable() + "_search";
    	file = procedure + ".sql";    	
    	Field primaryKey = generator.getFields().get(0);
    	
    	notepad = new StringBuilder();
    	
		notepad.append("Create Procedure " + procedure + separator);
    	notepad.append("(" + separator);
    	notepad.append("	@pit_parametrosXML		ntext," + separator);
    	notepad.append("	@piv_orderBy			varchar(100)" + separator);
    	notepad.append(")" + separator);
    	notepad.append("As" + separator);
    	notepad.append(separator);
    	notepad.append("-- VARIABLES SQL" + separator);
    	notepad.append("Declare @sqlBody varchar(6000),@sqlJoin varchar(500),@sqlWhere varchar(1500);" + separator);
    	notepad.append(separator);
    	notepad.append("-- VARIABLES DE FILTRO" + separator);
    	notepad.append("Declare @docXML int,@" + primaryKey.getNameDb() + " "  + primaryKey.getDataTypeLength() + ";" + separator);
    	notepad.append(separator);
    	notepad.append("Begin" + separator);
    	notepad.append(separator);
    	notepad.append("	--EXECUTE " + procedure  + " '<Record> <" + primaryKey.getNameDb() + ">1</" + primaryKey.getNameDb() + "> </Record>','';" + separator);
    	notepad.append(separator);
    	notepad.append("	--INICIALIZO LAS VARIABLES" + separator);
    	notepad.append("	Begin" + separator);
    	notepad.append(separator);
    	notepad.append("		--INICIALIZO LOS XML" + separator);
    	notepad.append("		EXEC sp_xml_preparedocument @docXML output, @pit_parametrosXML;" + separator);
    	notepad.append(separator);
    	notepad.append("	End;" + separator);
    	notepad.append(separator);
    	notepad.append("	--OBTENGO LOS FILTROS" + separator);
    	notepad.append("	Begin" + separator);
    	notepad.append(separator);
    	notepad.append("		--" + primaryKey.getNameDb() + separator);
    	notepad.append("		Begin Try" + separator);
    	notepad.append("			Set @" + primaryKey.getNameDb() + " =" + separator);
    	notepad.append("			(" + separator);
    	notepad.append("				Select " + primaryKey.getNameDb() + separator);
    	notepad.append("				From OpenXML (@docXML, 'Record',2)" + separator);
    	notepad.append("				With (" + primaryKey.getNameDb() + " " + primaryKey.getDataTypeLength() + ")" + separator);
    	notepad.append("			);" + separator);
    	notepad.append("		End Try" + separator);
    	notepad.append("		Begin Catch" + separator);
    	notepad.append("			Set @" + primaryKey.getNameDb() + " = 0;" + separator);
    	notepad.append("		End Catch;" + separator);    	
    	notepad.append(separator);
    	notepad.append("	End;" + separator);
    	notepad.append(separator);
    	notepad.append("	--CONTRUYO LA CONSULTA SQL" + separator);
    	notepad.append("	Begin" + separator);
    	notepad.append(separator);
    	notepad.append("		--BODY SQL" + separator);
    	notepad.append("		Begin" + separator);
    	notepad.append("			Set @sqlBody = '" + separator);
    	notepad.append("			Select" + separator);

    	count = 1;
    	for(Field c:generator.getFields())
    	{
    		lineCode = "			" + prefixTable + "." + c.getNameDb() + "			[" + c.getNameDb() + "]";
    		
    		notepad.append(lineCode + ((count == generator.getFields().size()) ? "';" : ",")  + separator);
    		
    		count++;
    	}
    	
    	notepad.append(separator);
    	
    	notepad.append("			Set @sqlJoin = '" + separator);
    	notepad.append("			From " + generator.getTable() + " [" + prefixTable + "]';" + separator);    	
    	notepad.append("		End;" + separator);    	
    	notepad.append(separator);
    	notepad.append("		--WHERE SQL" + separator);
    	notepad.append("		Begin" + separator);
    	notepad.append("			Set @sqlWhere = '" + separator);
    	notepad.append("			Where (1=1)';" + separator);
    	notepad.append(separator);
    	notepad.append("			--" + primaryKey.getNameDb() + separator);
    	notepad.append("			If @" + primaryKey.getNameDb() + " > 0" + separator);
    	notepad.append("			Begin" + separator);
    	notepad.append("				Set @sqlWhere = @sqlWhere + '" + separator);
    	notepad.append("				And " + prefixTable + "." + primaryKey.getNameDb() + " = ' + Cast(@" + primaryKey.getNameDb() + " As Varchar) + ''" + separator);    	
    	notepad.append("			End;" + separator);
    	notepad.append("		End;" + separator);    	
    	notepad.append(separator);
    	notepad.append("		--ORDER BY" + separator);
    	notepad.append("		Begin" + separator);
    	notepad.append("			If @piv_orderBy = ''" + separator);
    	notepad.append("			Begin" + separator);
    	notepad.append("				Set @piv_orderBy = '" + primaryKey.getNameDb() + " ASC';" + separator);    	
    	notepad.append("			End;" + separator);
    	notepad.append(separator);
    	notepad.append("			Set @piv_orderBy = '" + separator);
    	notepad.append("			Order By ' + @piv_orderBy;" + separator);    	
    	notepad.append("		End;" + separator);    	
    	notepad.append(separator);
    	notepad.append("	End;" + separator);
    	notepad.append(separator);
    	notepad.append("	--EJECUTO LA CONSULTA SQL" + separator);
    	notepad.append("	Begin" + separator);
    	notepad.append(separator);
    	notepad.append("		--Print(@sqlBody + @sqlJoin + @sqlWhere + @piv_orderBy);" + separator);
    	notepad.append(separator);    	
    	notepad.append("		Execute(@sqlBody + @sqlJoin + @sqlWhere + @piv_orderBy);" + separator);
    	notepad.append(separator);
    	notepad.append("	End;" + separator);
    	notepad.append(separator);
    	notepad.append("End;" + separator);
    	notepad.append(separator);
    	notepad.append("Go");
    	
    	generator.getNotepads().put(file, notepad);
	}	

}
