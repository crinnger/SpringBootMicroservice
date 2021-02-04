package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.exception.NotFoundException;
import br.com.crinnger.SpringBootMicroservice.mappers.BeerMapper;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
	
	@Autowired
	private BeerRepository beerRepository;

	@Autowired
	private BeerMapper beerMapper;  
 
	@Override
	public BeerDto getBeerByID(UUID beerId) {
		// TODO Auto-generated method stub
		System.out.println(beerId.toString());
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		System.out.println(beer);
		BeerDto beerDto= beerMapper.beerToBeerDto(beer);
		System.out.println(beerDto);
		return beerDto;
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {
		// TODO Auto-generated method stub
		Beer beer= beerMapper.beerDtoToBeer(beerDto); 
		BeerDto newbeerDto= beerMapper.beerToBeerDto(beerRepository.save(beer));
		return newbeerDto;
		
	}

	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().toString());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());
		
		return beerMapper.beerToBeerDto(beerRepository.save(beer));
	}

	@Override
	public void deleteById(UUID beerId) {
		// TODO Auto-generated method stub~
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		beerRepository.delete(beer);
		log.debug("Deletando"); 
		
	}

}
