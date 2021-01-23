package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.web.model.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerByID(UUID beerId) {
		// TODO Auto-generated method stub
		BeerDto beer= BeerDto.builder().id(UUID.randomUUID())
		.beerName("Heineken")
		.beerStyle(BeerStyleEnum.LAGER).build();
		return beer;
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		// TODO Auto-generated method stub
		BeerDto beer= BeerDto.builder().id(UUID.randomUUID())
		.beerName(beerDto.getBeerName())
		.beerStyle(beerDto.getBeerStyle()).build();
		return beer;
		
	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO Auto-generated method stub
		log.debug("Deletando");
		
	}

}
