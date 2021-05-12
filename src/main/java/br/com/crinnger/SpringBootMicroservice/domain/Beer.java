package br.com.crinnger.SpringBootMicroservice.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID; 

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor 
@AllArgsConstructor
@Builder 
@Entity
public class Beer {
	@Id
	@GeneratedValue(generator="UUID") 
	@Type(type="org.hibernate.type.UUIDCharType")
	@GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 36, columnDefinition = "varchar(36)", updatable = false,nullable = false)
	private UUID id;
	
	@Version
	private Long version;
	
	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp createdDate;
	
	@UpdateTimestamp
	private Timestamp lastModifiedDate;
	
	private String beerName;
	
	private String beerStyle;
	
	@Column(unique = true)
	private Long upc;   
	private BigDecimal price;
	
	private Long minOnHand; 
	
	private Long quantityToBrew; 
	
}
