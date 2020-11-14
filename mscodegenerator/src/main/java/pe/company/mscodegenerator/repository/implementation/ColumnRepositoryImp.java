package pe.company.mscodegenerator.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pe.company.mscodegenerator.application.domain.Column;
import pe.company.mscodegenerator.application.domain.ConnectionDb;
import pe.company.mscodegenerator.cross.utils.ConvertFormat;
import pe.company.mscodegenerator.cross.utils.SupportDatabase;
import pe.company.mscodegenerator.cross.variables.DbType;
import pe.company.mscodegenerator.repository.interfaces.ColumnRepositoryInt;

@Repository
public class ColumnRepositoryImp implements ColumnRepositoryInt 
{
	@SuppressWarnings("deprecation")
	@Override
	public List<Column> getSearch(ConnectionDb connection,String table) 
	{	
		JdbcTemplate jdbcTemplate = new JdbcTemplate(SupportDatabase.getDataSourceByConnectionString(connection.getDbType(),connection.getServer(),connection.getDataBase(),connection.getUserName(),connection.getPassword()));
		
		String sql = this.getSql(connection.getDbType());
		
		return jdbcTemplate.query(sql,new Object[] { table },new ColumnSelectRowMapper());		
	}
	
	static class ColumnSelectRowMapper implements RowMapper<Column> 
	{
		@Override
		public Column mapRow(ResultSet rs, int row) throws SQLException 
		{
			Column o = new Column();
			
			o.setId(rs.getString("Id"));
			o.setName(rs.getString("Name"));
			o.setDataType(rs.getString("DataType"));
			o.setLength(rs.getString("Length"));
			o.setPrecision(rs.getInt("Precision"));
			o.setIsNull(rs.getBoolean("IsNull"));
			
			//Set DataType
			String dataTypeLength = o.getDataType();
			
			if(!ConvertFormat.IsNullOrEmpty(o.getLength()))
			{
				if(o.getDataType().toLowerCase().contains("char"))
					dataTypeLength = o.getDataType() + "(" + o.getLength() + ")";
				else
				{
					if(o.getPrecision()>0)
						dataTypeLength = o.getDataType() + "(" + o.getLength() + "," + o.getPrecision() + ")";	
				}				
			}
			
			o.setDataTypeLength(dataTypeLength);
			
			return o;
		}
	}
	
	private String getSql(String dbType)
	{
		String sql = null;
		
		switch(dbType)
		{
			case DbType.SqlServer:
				sql = "Select " + 
						"ORDINAL_POSITION [Id], " +
						"COLUMN_NAME [Name], " +
						"DATA_TYPE [DataType], " +
						"(CASE WHEN DATA_TYPE Like '%Char%' THEN CHARACTER_MAXIMUM_LENGTH ELSE NUMERIC_PRECISION END) [Length], " +
						"NUMERIC_SCALE [Precision], " +
						"(CASE WHEN IS_NULLABLE='YES' THEN 1 ELSE 0 END) [IsNull] " +
						"FROM INFORMATION_SCHEMA.COLUMNS " +
						"WHERE TABLE_SCHEMA + '.' + TABLE_NAME=? " +
						"Order By ORDINAL_POSITION ";				
				break;
		}
		
		return sql;
	}
}
