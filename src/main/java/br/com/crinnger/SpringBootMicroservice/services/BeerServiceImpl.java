package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerByID(UUID beerId) {
		// TODO Auto-generated method stub
		return BeerDto.builder().id(UUID.randomUUID())
				.beerName("Heineken")
				.beerStyle("Lager").build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		// TODO Auto-generated method stub
		return BeerDto.builder()
				.id(UUID.randomUUID())
				.build();
		
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
