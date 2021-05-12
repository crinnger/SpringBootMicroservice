package br.com.crinnger.SpringBootMicroservice.mappers;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.services.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by jt on 2019-06-08.
 */
public abstract class BeerMapperDecorator implements BeerMapper {

    private BeerInventoryService beerInventoryService;

    private BeerMapper mapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setMapper(BeerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
       return mapper.beerToBeerDto(beer);
    }

    @Override
    public BeerDto beerToBeerDtoWithInventory(Beer beer) {
        BeerDto dto = mapper.beerToBeerDto(beer);
        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return mapper.beerDtoToBeer(beerDto);
    }
}