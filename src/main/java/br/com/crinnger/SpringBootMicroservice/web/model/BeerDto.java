package br.com.crinnger.SpringBootMicroservice.web.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
	
	@Null
	private Long version;
	
	@Null
	private Timestamp createdDate;

	@Null
	private Timestamp lastModifiedDate;
	
	@NotBlank
	@NotNull
	private String beerName;
	
	@NotNull	
	private BeerStyleEnum beerStyle;
	
	@Positive
	@NotNull
	private Long upc;   
	
	@NotNull
	@Positive
	private BigDecimal price;
	
	private Long minOnHand; 
	
	private Long quantityToBrew; 
	 
}
