package pe.company.mscodegenerator.api.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TableResponse implements Serializable 
{
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	private String schema;
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}	
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
