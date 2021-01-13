package br.com.crinnger.SpringBootMicroservice.web.controller.v2;

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
import br.com.crinnger.SpringBootMicroservice.services.v2.BeerServiceV2;
import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.web.model.v2.BeerDtoV2;

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {
	
	private final BeerServiceV2 beerService;
	
	public BeerControllerV2(BeerServiceV2 beerService) {
		this.beerService = beerService;
	}
	
	@GetMapping({"/{beerId}"})
	public ResponseEntity<BeerDtoV2> getBeer(@PathVariable("beerId") UUID beerId){
		return new ResponseEntity<BeerDtoV2>(beerService.getBeerByID(beerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<BeerDtoV2>  handlePost(@RequestBody BeerDtoV2 beerDto) {
		BeerDtoV2 saveBeer= this.beerService.saveNewBeer(beerDto);
		HttpHeaders headers=new HttpHeaders(); 
		headers.add("Location", "/api/v1/beer/" + saveBeer.getId().toString());
		return new ResponseEntity<BeerDtoV2>(headers,HttpStatus.CREATED);  
	}
	
	@PutMapping({"/{beerId}"})
	public ResponseEntity<BeerDtoV2>  handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDtoV2 beerDto) {
		beerService.updateBeer(beerId,beerDto); 
		return new ResponseEntity<BeerDtoV2>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		this.beerService.deleteById(beerId);		 
	}

}
