package pe.company.mscodegenerator.application.mapper;

import pe.company.mscodegenerator.api.request.FieldRequest;
import pe.company.mscodegenerator.application.domain.Field;

public class FieldMapper 
{
	public static Field MapperFromFieldRequest(FieldRequest i) 
	{
		Field o = new Field();
		
		o.setId(i.getId());
		o.setName(i.getName());
		o.setDataType(i.getDataType());
		o.setNameDb(i.getNameDb());
		o.setDataTypeDb(i.getDataTypeDb());
		o.setDataTypeLength(i.getDataTypeLength());
		o.setLength(i.getLength());
		o.setPrecision(i.getPrecision());
		o.setIsNull(i.getIsNull());
		
		return o;
	}
}
