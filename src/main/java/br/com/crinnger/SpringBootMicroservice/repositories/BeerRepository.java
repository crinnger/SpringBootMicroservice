package br.com.crinnger.SpringBootMicroservice.repositories;

import java.util.Optional;
import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{

    Page<Beer> findAllByBeerName(String beerName, Pageable pageable);

    Page<Beer> findAllByBeerStyle(BeerStyleEnum beerStyle, Pageable pageable);

    Page<Beer> findAllByBeerNameAndBeerStyle(String beerName, BeerStyleEnum beerStyle, Pageable pageable);

    Optional<Beer> findByUpc(Long beerUpc);
}
