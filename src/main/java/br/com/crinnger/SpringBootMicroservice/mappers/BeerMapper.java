package br.com.crinnger.SpringBootMicroservice.mappers;

import org.mapstruct.Mapper;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;

@Mapper(componentModel = "spring", uses = {DateMapper.class})
public interface BeerMapper { 
	BeerDto beerToBeerDto(Beer beer); 
	Beer beerDtoToBeer(BeerDto dto); 
}
