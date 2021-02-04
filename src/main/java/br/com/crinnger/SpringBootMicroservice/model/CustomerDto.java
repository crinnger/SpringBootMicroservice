package br.com.crinnger.SpringBootMicroservice.model;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
	@NotNull	
	private UUID id;
	
	@Null
	private Long version;
	
	@Null
	private OffsetDateTime createdDate;

	@Null
	private OffsetDateTime lastModifiedDate;
	
	@NotNull
	@NotBlank
	@Size(min=3,max=100)
	private String name;
}
