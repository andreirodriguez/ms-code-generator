package pe.company.mscodegenerator.api.request;

import java.util.ArrayList;
import java.util.List;

public class GeneratorRequest 
{
	private String project;
	private String dbType;
	private String programmingLanguage;	
	private String table;
	private String entity;
	private List<FieldRequest> fields = new ArrayList<FieldRequest>();
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDbType() {
		return dbType;
	}
	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public List<FieldRequest> getFields() {
		return fields;
	}
	public void setFields(List<FieldRequest> fields) {
		this.fields = fields;
	}
}
