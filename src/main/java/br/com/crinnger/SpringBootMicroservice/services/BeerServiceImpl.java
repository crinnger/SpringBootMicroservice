package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;
import java.util.stream.Collectors;

import br.com.crinnger.SpringBootMicroservice.model.BeerPagedList;
import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.exception.NotFoundException;
import br.com.crinnger.SpringBootMicroservice.mappers.BeerMapper;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
	
	@Autowired
	private BeerRepository beerRepository;

	@Autowired
	private BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerUpcCache", key="#beerId",condition = "#showInvetory == false")
	@Override
	public BeerDto getBeerByUpc(Long beerUpc, Boolean showInvetory) {
		Beer beer = beerRepository.findByUpc(beerUpc).orElseThrow(NotFoundException::new);
		BeerDto beerDto;
		if (showInvetory){
			beerDto= beerMapper.beerToBeerDtoWithInventory(beer);
		} else {
			beerDto= beerMapper.beerToBeerDto(beer);
		}
		return beerDto;
	}

	@Cacheable(cacheNames = "beerCache", key="#beerId",condition = "#showInvetory == false")
	@Override
	public BeerDto getBeerByID(UUID beerId, Boolean showInvetory) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		BeerDto beerDto;
		if (showInvetory){
			beerDto= beerMapper.beerToBeerDtoWithInventory(beer);
		} else {
			beerDto= beerMapper.beerToBeerDto(beer);
		}
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

	@Cacheable(cacheNames = "beerListCache", condition = "#showInvetory == false")
	@Override
	public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest,Boolean showInvetory) {

		BeerPagedList beerPagedList;
		Page<Beer> beerPage;

		if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			//search both
			beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
		} else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
			//search beer_service name
			beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
		} else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
			//search beer_service style
			beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
		} else {
			beerPage = beerRepository.findAll(pageRequest);
		}

		if(showInvetory){
			beerPagedList = new BeerPagedList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDtoWithInventory)
					.collect(Collectors.toList()),
					PageRequest
							.of(beerPage.getPageable().getPageNumber(),
									beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		} else {
			beerPagedList = new BeerPagedList(beerPage
					.getContent()
					.stream()
					.map(beerMapper::beerToBeerDto)
					.collect(Collectors.toList()),
					PageRequest
							.of(beerPage.getPageable().getPageNumber(),
									beerPage.getPageable().getPageSize()),
					beerPage.getTotalElements());
		}
		return beerPagedList;
	}

}
