package pe.company.mscodegenerator.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/tables")
public class TableController 
{	
	@GetMapping("/search")
	public ResponseEntity<String> getTablesByBD() 
	{		 
		return new ResponseEntity<>("Hello world!",HttpStatus.OK);
	}	
}
