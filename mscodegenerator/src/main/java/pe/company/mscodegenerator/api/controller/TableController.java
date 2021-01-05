package pe.company.mscodegenerator.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import pe.company.mscodegenerator.service.interfaces.TableServiceInt;
import pe.company.mscodegenerator.application.domain.Table;
import pe.company.mscodegenerator.application.mapper.ConnectionDbMapper;
import pe.company.mscodegenerator.application.mapper.TableMapper;
import pe.company.mscodegenerator.api.request.ConnectionDbRequest;
import pe.company.mscodegenerator.api.response.TableResponse;

 
@RestController
@RequestMapping("/tables")
public class TableController 
{
	@Autowired
	private TableServiceInt tableService;
	
	@PostMapping("/search")
	public ResponseEntity<List<TableResponse>> getTablesByBD(@RequestBody ConnectionDbRequest request) 
	{		 
 		List<Table> l = tableService.getSelect(ConnectionDbMapper.MapperFromConnectionDbRequest(request));
		
		return new ResponseEntity<>(TableMapper.MapperToListTableResponse(l),HttpStatus.OK);
	}	
}
