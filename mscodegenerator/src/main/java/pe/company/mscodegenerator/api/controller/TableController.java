package pe.company.mscodegenerator.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.mscodegenerator.service.interfaces.TableServiceInt;
import pe.company.mscodegenerator.application.domain.Table;
import pe.company.mscodegenerator.application.mapper.TableMapper;
import pe.company.mscodegenerator.api.request.TableRequest;
import pe.company.mscodegenerator.api.response.TableResponse;

 
@RestController
@RequestMapping("/tables")
public class TableController 
{
	@Autowired
	private TableServiceInt tableService;
	
	@PostMapping("/search")
	public ResponseEntity<List<TableResponse>> getTablesByBD(@RequestBody TableRequest request) 
	{		 
 		List<Table> l = tableService.getSelect(request.getDbType(),request.getConnectionString());
		
		List<TableResponse> o = new ArrayList<TableResponse>();
		
		for(Table i:l)
			o.add(TableMapper.MapperToTableResponse(i));
		
		return new ResponseEntity<>(o,HttpStatus.OK);
	}	
}
