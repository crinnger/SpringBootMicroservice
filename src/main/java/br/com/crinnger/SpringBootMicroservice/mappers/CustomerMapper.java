package br.com.crinnger.SpringBootMicroservice.mappers;

import org.mapstruct.Mapper;

import br.com.crinnger.SpringBootMicroservice.domain.Customer;
import br.com.crinnger.SpringBootMicroservice.model.CustomerDto;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
	
	CustomerDto customerToCustomerDto(Customer customer);
	Customer customerDtoToCustomer(CustomerDto customer); 

}
