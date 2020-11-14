package pe.company.mscodegenerator.repository.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import pe.company.mscodegenerator.repository.interfaces.TableRepositoryInt;
import pe.company.mscodegenerator.application.domain.Table;


@Repository
public class TableRepositoryImp implements TableRepositoryInt
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Table> getSearch(String dbType,String connectionString) 
	{	
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	    dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    dataSourceBuilder.url("jdbc:sqlserver://localhost\\SQL2019;databaseName=PRUEBA");
	    dataSourceBuilder.username("sa");
	    dataSourceBuilder.password("Theo@ndre");
		
		JdbcTemplate jdbcTemplate1 = new JdbcTemplate(dataSourceBuilder.build());
		
		String sql = null;
		
		sql = "Select " + 
				"Table_Schema + '.' + Table_Name [Id]," +
				"Table_Schema [Schema]," +
				"Table_Name [Name]" +
				"FROM INFORMATION_SCHEMA.TABLES " +
				"Where Table_Type like '%Table%' " +
				"Order By TABLE_NAME ";
		
		return jdbcTemplate1.query(sql,new TableSelectRowMapper());		
	}
	
	static class TableSelectRowMapper implements RowMapper<Table> 
	{
		@Override
		public Table mapRow(ResultSet rs, int row) throws SQLException 
		{
			Table o = new Table();
			
			o.setId(rs.getString("Id"));
			o.setSchema(rs.getString("Schema"));
			o.setName(rs.getString("Name"));
			
			return o;
		}
	}
}
