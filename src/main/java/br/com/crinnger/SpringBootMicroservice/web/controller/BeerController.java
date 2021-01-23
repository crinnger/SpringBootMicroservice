package br.com.crinnger.SpringBootMicroservice.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.crinnger.SpringBootMicroservice.services.BeerService;
import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;

@Validated
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;
	
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}
	
	@GetMapping({"/{beerId}"})
	public ResponseEntity<BeerDto> getBeer(@NotNull  @PathVariable("beerId") UUID beerId){
		BeerDto beer = beerService.getBeerByID(beerId); 
		return new ResponseEntity<BeerDto>(beer, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDto>  handlePost(@Valid @RequestBody BeerDto beerDto) {
		BeerDto saveBeer= this.beerService.saveNewBeer(beerDto);
		HttpHeaders headers=new HttpHeaders();		
		headers.add("Location", "/api/v1/beer/" + saveBeer.getId().toString());
		return new ResponseEntity<BeerDto>(headers,HttpStatus.CREATED);  
	}
	
	@PutMapping({"/{beerId}"})
	public ResponseEntity<BeerDto>  handleUpdate(@NotNull @PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId,beerDto); 
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@NotNull @PathVariable("beerId") UUID beerId) {
		this.beerService.deleteById(beerId);		 
	}

}
