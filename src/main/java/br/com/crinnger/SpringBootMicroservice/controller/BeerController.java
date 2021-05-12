package br.com.crinnger.SpringBootMicroservice.controller;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.crinnger.SpringBootMicroservice.model.BeerPagedList;
import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.crinnger.SpringBootMicroservice.mappers.BeerMapper;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;
import br.com.crinnger.SpringBootMicroservice.services.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/")
@Api(tags = "Beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;

	private static final Integer DEFAULT_PAGE_NUMBER = 0;
	private static final Integer DEFAULT_PAGE_SIZE = 25;

	@GetMapping(produces = { "application/json" },path = "beer")
	public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
												   @RequestParam(value = "pageSize", required = false) Integer pageSize,
												   @RequestParam(value = "beerName", required = false) String beerName,
												   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
												   @RequestParam(value = "showInventory", required = false) Boolean showInvetory){

		if(showInvetory== null){
			showInvetory=false;
		}
		if (pageNumber == null || pageNumber < 0){
			pageNumber = DEFAULT_PAGE_NUMBER;
		}

		if (pageSize == null || pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize),showInvetory);

		return new ResponseEntity<>(beerList, HttpStatus.OK);
	}

	@GetMapping("beer/{beerId}")
	@ApiOperation("Get")
	public ResponseEntity<BeerDto> getBeer(@NotNull  @PathVariable("beerId") UUID beerId,
										   @RequestParam(value = "showInventory", required = false) Boolean showInvetory){
		if(showInvetory== null){
			showInvetory=false;
		}
		BeerDto beer = beerService.getBeerByID(beerId,showInvetory);
		return new ResponseEntity<BeerDto>(beer, HttpStatus.OK);
	}

	@GetMapping("beerUpc/{beerUpc}")
	@ApiOperation("Get")
	public ResponseEntity<BeerDto> getBeerByUpc(@NotNull  @PathVariable("beerUpc") Long beerUpc,
										   @RequestParam(value = "showInventory", required = false) Boolean showInvetory){
		if(showInvetory== null){
			showInvetory=false;
		}
		BeerDto beer = beerService.getBeerByUpc(beerUpc,showInvetory);
		return new ResponseEntity<BeerDto>(beer, HttpStatus.OK);
	}
	
	@PostMapping(path = "beer")
	@ApiOperation("New")
	public ResponseEntity<BeerDto>  handlePost(@Valid @RequestBody BeerDto beerDto) {
		BeerDto saveBeer= beerService.saveNewBeer(beerDto);
		HttpHeaders headers=new HttpHeaders();		
		//headers.add("Location", "/api/v1/beer/" + saveBeer.getId().toString());
		//headers.add("Location", saveBeer.toString());
		return new ResponseEntity<BeerDto>(saveBeer,headers,HttpStatus.CREATED);
	}
	
	@PutMapping("beer/{beerId}")
	@ApiOperation("Update")
	public ResponseEntity<BeerDto>  handleUpdate(@NotNull @PathVariable("beerId") UUID beerId,@Valid @RequestBody BeerDto beerDto) {
		beerService.updateBeer(beerId,beerDto); 
		return new ResponseEntity<BeerDto>(HttpStatus.NO_CONTENT); 
	}
	
	@DeleteMapping("beer/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Delete")
	public void deleteBeer(@NotNull @PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);		 
	}

}
