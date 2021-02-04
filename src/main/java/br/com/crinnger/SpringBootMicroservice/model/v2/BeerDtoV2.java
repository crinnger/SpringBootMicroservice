package br.com.crinnger.SpringBootMicroservice.model.v2;

import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 {
	private UUID id;
	private String beerName;
	private BeerStyleEnum beerStyle;
	private Long upc;
	 
}
