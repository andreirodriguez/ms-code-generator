package pe.company.mscodegenerator.application.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.company.mscodegenerator.application.domain.Table;

import pe.company.mscodegenerator.api.response.TableResponse;

public class TableMapper 
{
	public static TableResponse MapperToTableResponse(Table i) 
	{
		TableResponse o = new TableResponse();
		
		o.setId(i.getId());
		o.setSchema(i.getSchema());
		o.setName(i.getName());
		
		return o;
	}
	
	public static List<TableResponse> MapperToListTableResponse(List<Table> l) 
	{
		List<TableResponse> o = new ArrayList<TableResponse>();
		
		for(Table i:l)
			o.add(TableMapper.MapperToTableResponse(i));
		
		return o;
	}	

}
