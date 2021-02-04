package br.com.crinnger.SpringBootMicroservice.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.crinnger.SpringBootMicroservice.mappers.BeerMapper;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;
import br.com.crinnger.SpringBootMicroservice.services.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/beer")
@Api(tags = "Beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;	
		
	@GetMapping({"/{beerId}"})
	@ApiOperation("Get")
	public ResponseEntity<BeerDto> getBeer(@NotNull  @PathVariable("beerId") UUID beerId){
		BeerDto beer = beerService.getBeerByID(beerId); 
		return new ResponseEntity<BeerDto>(beer, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation("New")
	public ResponseEntity<BeerDto>  handlePost(@Valid @RequestBody BeerDto beerDto) {
		BeerDto saveBeer= beerService.saveNewBeer(beerDto);
		HttpHeaders headers=new HttpHeaders();		
		//headers.add("Location", "/api/v1/beer/" + saveBeer.getId().toString());
		headers.add("Location", saveBeer.toString());
		return new ResponseEntity<BeerDto>(headers,HttpStatus.CREATED);  
	}
	
	@PutMapping({"/{beerId}"})
	@ApiOperation("Update")
	public ResponseEntity<BeerDto>  handleUpdate(@NotNull @PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId,beerDto); 
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Delete")
	public void deleteBeer(@NotNull @PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);		 
	}

}
