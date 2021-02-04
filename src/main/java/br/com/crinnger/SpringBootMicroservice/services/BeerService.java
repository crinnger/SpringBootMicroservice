package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.model.BeerDto;

public interface BeerService {

	BeerDto getBeerByID(UUID beerId);

	BeerDto saveNewBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId,BeerDto beerDto);

	void deleteById(UUID beerId);

}
