package br.com.crinnger.SpringBootMicroservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;
import br.com.crinnger.SpringBootMicroservice.web.model.BeerStyleEnum;

@Component
public class BeerLoader implements CommandLineRunner {
	
	private final BeerRepository beerRepository;
	
	public BeerLoader (BeerRepository beerRepository) {
		this.beerRepository=beerRepository;
	}
	
		
	@Override
	public void run(String... args) throws Exception {
		this.loadBeerObjects();
	}

	private void loadBeerObjects() {
		if(beerRepository.count()==0) {
			this.beerRepository.save(Beer.builder()
					.beerName("Bud")
					.beerStyle(BeerStyleEnum.LAGER)
					.quantityToBrew(200)
					.upc(4554545L)
					.price(new BigDecimal("12.95"))
					.build());
			this.beerRepository.save(Beer.builder()
					.beerName("heineken")
					.beerStyle(BeerStyleEnum.LAGER)
					.quantityToBrew(100)
					.upc(4554546L)
					.price(new BigDecimal("15"))
					.build());
		}		
	}	
}
