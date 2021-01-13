package br.com.crinnger.SpringBootMicroservice.services.v2;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.web.model.v2.BeerDtoV2;
import br.com.crinnger.SpringBootMicroservice.web.model.v2.BeerStyleEnum;
import lombok.extern.slf4j.Slf4j;

@Service 
@Slf4j 
public class BeerServiceImplV2 implements BeerServiceV2 {  

	@Override
	public BeerDtoV2 getBeerByID(UUID beerId) {
		// TODO Auto-generated method stub
		return BeerDtoV2.builder().id(UUID.randomUUID())
				.beerName("Heineken")
				.beerStyle(BeerStyleEnum.ALE).build();
	}

	@Override
	public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
		// TODO Auto-generated method stub
		return BeerDtoV2.builder()
				.id(UUID.randomUUID())
				.build();
		
	}

	@Override
	public void updateBeer(UUID beerId, BeerDtoV2 beerDto) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO Auto-generated method stub
		log.debug("Deletando");
		
	}

}
