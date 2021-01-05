package pe.company.mscodegenerator.api.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ColumnResponse implements Serializable 
{
	private String id;
	private String name;
	private String dataType;
	private String nameEntity;
	private String dataTypeEntity;	
	private String length;
	private Integer precision;
	private String dataTypeLength;	
	private Boolean isNull;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNameEntity() {
		return nameEntity;
	}
	public void setNameEntity(String nameEntity) {
		this.nameEntity = nameEntity;
	}	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataTypeEntity() {
		return dataTypeEntity;
	}
	public void setDataTypeEntity(String dataTypeEntity) {
		this.dataTypeEntity = dataTypeEntity;
	}	
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public Integer getPrecision() {
		return precision;
	}
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}
	public String getDataTypeLength() {
		return dataTypeLength;
	}
	public void setDataTypeLength(String dataTypeLength) {
		this.dataTypeLength = dataTypeLength;
	}	
	public Boolean getIsNull() {
		return isNull;
	}
	public void setIsNull(Boolean isNull) {
		this.isNull = isNull;
	}	
}
