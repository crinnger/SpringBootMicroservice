package br.com.crinnger.SpringBootMicroservice.web.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
	private UUID id;
	private String beerName;
	private BeerStyleEnum beerStyle;
	private Long upc;   
	 
}
