package br.com.crinnger.SpringBootMicroservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import br.com.crinnger.SpringBootMicroservice.model.format.LocalDateDeserializer;
import br.com.crinnger.SpringBootMicroservice.model.format.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data; 
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto implements Serializable {

	static final long serialVersionUID = 4026496681017885886L;
	
	@JsonProperty("id")
	@Null
	private UUID id;
	
	@Null
	private Long version;
	
	@Null
	@JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ssZ",shape =JsonFormat.Shape.STRING )
	private OffsetDateTime createdDate;

	@Null
	@JsonFormat(pattern ="yyyy-MM-dd'T'HH:mm:ssZ",shape =JsonFormat.Shape.STRING )
	private OffsetDateTime lastModifiedDate;
	
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
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	private BigDecimal price;
	
	private Long minOnHand; 
	
	private Long quantityToBrew;

	private Integer quantityOnHand;

//	@JsonSerialize(using=LocalDateSerializer.class)
//	@JsonDeserialize(using=LocalDateDeserializer.class)
//	private LocalDate localDate; 
	 
}
