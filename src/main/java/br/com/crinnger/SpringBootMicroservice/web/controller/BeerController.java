package br.com.crinnger.SpringBootMicroservice.web.controller;

import java.awt.image.PixelGrabber;
import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;
	
	public BeerController(BeerService beerService) {
		this.beerService = beerService;
	}
	
	@GetMapping({"/{beerId}"})
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId){
		return new ResponseEntity<>(beerService.getBeerByID(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDto>  handlePost(@RequestBody BeerDto beerDto) {
		BeerDto saveBeer= this.beerService.saveNewBeer(beerDto);
		HttpHeaders headers=new HttpHeaders(); 
		headers.add("Location", "/api/v1/beer/" + saveBeer.getId().toString());
		return new ResponseEntity<BeerDto>(headers,HttpStatus.CREATED);  
	}
	
	@PutMapping({"/{beerId}"})
	public ResponseEntity<BeerDto>  handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId,beerDto); 
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		this.beerService.deleteById(beerId);		 
	}

}
