package pe.company.mscodegenerator.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.company.mscodegenerator.api.request.ColumnRequest;
import pe.company.mscodegenerator.api.response.ColumnResponse;
import pe.company.mscodegenerator.application.domain.Column;
import pe.company.mscodegenerator.application.mapper.ColumnMapper;
import pe.company.mscodegenerator.application.mapper.ConnectionDbMapper;
import pe.company.mscodegenerator.service.interfaces.ColumnServiceInt;

@RestController
@RequestMapping("/columns")
public class ColumnController 
{
	@Autowired
	private ColumnServiceInt columnService;
	
	@PostMapping("/search")
	public ResponseEntity<List<ColumnResponse>> getTablesByBD(@RequestBody ColumnRequest request) 
	{		 
 		List<Column> l = columnService.getSelect(ConnectionDbMapper.MapperFromConnectionDbRequest(request.getConnectionDb()),request.getTable());
		
		return new ResponseEntity<>(ColumnMapper.MapperToListColumnResponse(l),HttpStatus.OK);
	}	
}
