package pe.company.mscodegenerator.application.mapper;

import java.util.ArrayList;
import java.util.List;

import pe.company.mscodegenerator.api.response.ColumnResponse;
import pe.company.mscodegenerator.application.domain.Column;

public class ColumnMapper {
	public static ColumnResponse MapperToColumnResponse(Column i) 
	{
		ColumnResponse o = new ColumnResponse();
		
		o.setId(i.getId());
		o.setName(i.getName());
		o.setDataType(i.getDataType());
		o.setLength(i.getLength());
		o.setPrecision(i.getPrecision());
		o.setDataTypeLength(i.getDataTypeLength());
		o.setIsNull(i.getIsNull());
		
		return o;
	}
	
	public static List<ColumnResponse> MapperToListColumnResponse(List<Column> l) 
	{
		List<ColumnResponse> o = new ArrayList<ColumnResponse>();
		
		for(Column i:l)
			o.add(ColumnMapper.MapperToColumnResponse(i));
		
		return o;
	}	
}
