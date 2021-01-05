package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerByID(UUID beerId);

}
