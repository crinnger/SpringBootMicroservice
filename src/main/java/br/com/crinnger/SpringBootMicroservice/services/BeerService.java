package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.model.BeerPagedList;
import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

public interface BeerService {

	BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInvetory);

	BeerDto getBeerByID(UUID beerId,Boolean showInvetory);

	BeerDto saveNewBeer(BeerDto beerDto);
	
	BeerDto updateBeer(UUID beerId,BeerDto beerDto);

	void deleteById(UUID beerId);

	BeerDto getBeerByUpc(Long beerUpc, Boolean showInvetory);
}
