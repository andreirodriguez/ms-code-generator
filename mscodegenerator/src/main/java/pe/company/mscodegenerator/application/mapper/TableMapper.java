package pe.company.mscodegenerator.application.mapper;

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

}
