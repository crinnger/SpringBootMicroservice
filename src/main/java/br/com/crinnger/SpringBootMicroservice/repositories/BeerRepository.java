package br.com.crinnger.SpringBootMicroservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{

}
