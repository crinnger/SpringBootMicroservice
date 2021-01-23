package br.com.crinnger.SpringBootMicroservice.web.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
	@Null
	private UUID id;
	
	@NotBlank
	@NotNull
	private String beerName;
	
	@NotNull	
	private BeerStyleEnum beerStyle;
	
	@Positive
	private Long upc;   
	 
}
