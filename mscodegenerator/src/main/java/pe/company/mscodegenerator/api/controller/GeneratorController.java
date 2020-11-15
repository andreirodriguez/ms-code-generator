package pe.company.mscodegenerator.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.company.mscodegenerator.api.request.GeneratorRequest;
import pe.company.mscodegenerator.application.domain.Generator;
import pe.company.mscodegenerator.application.mapper.GeneratorMapper;
import pe.company.mscodegenerator.service.interfaces.GeneratorServiceInt;

@RestController
@RequestMapping("/generator")
public class GeneratorController 
{
	@Autowired
	private GeneratorServiceInt generatorService;
	
	@PostMapping()
	public ResponseEntity<Generator> setGenerate(@RequestBody GeneratorRequest request) 
	{	
		Generator generator = GeneratorMapper.MapperFromGeneratorRequest(request);
		
 		generatorService.setGenerate(generator);
		
		return new ResponseEntity<>(generator,HttpStatus.OK);
	}	
}
