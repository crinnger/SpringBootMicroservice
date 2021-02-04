package br.com.crinnger.SpringBootMicroservice.repositories;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.crinnger.SpringBootMicroservice.domain.Beer;

@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID>{

}
