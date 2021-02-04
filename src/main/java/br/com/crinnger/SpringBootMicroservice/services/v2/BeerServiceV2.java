package br.com.crinnger.SpringBootMicroservice.services.v2;

import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.model.v2.BeerDtoV2;

public interface BeerServiceV2 {
	BeerDtoV2 getBeerByID(UUID beerId);

	BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);
	void updateBeer(UUID beerId,BeerDtoV2 beerDto);

	void deleteById(UUID beerId); 
}
