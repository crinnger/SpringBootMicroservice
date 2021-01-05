package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;

@Service 
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerByID(UUID beerId) {
		// TODO Auto-generated method stub
		return BeerDto.builder().id(UUID.randomUUID())
				.beerName("Heineken")
				.beerStyle("Lager").build();
	}

}
