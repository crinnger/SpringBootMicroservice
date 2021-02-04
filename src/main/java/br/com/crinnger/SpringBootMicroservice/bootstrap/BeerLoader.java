package br.com.crinnger.SpringBootMicroservice.bootstrap;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;

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
					.beerStyle("LAGER")
					.quantityToBrew(200L)
					.upc(4554545L)
					.price(new BigDecimal("12.95"))
					.build());
			this.beerRepository.save(Beer.builder()
					.beerName("heineken")
					.beerStyle("PILSNER")
					.quantityToBrew(100L)
					.upc(4554546L)
					.price(new BigDecimal("15"))
					.build());
		}		
	}	
}
