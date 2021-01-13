package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import br.com.crinnger.SpringBootMicroservice.web.model.CustomerDto;

public interface CustomerService {
	CustomerDto getCustomerById(UUID id);

	CustomerDto saveCustomer(CustomerDto customerDto);

	void updateCustomer(UUID customerId, CustomerDto customerDto);

	void deleteByID(UUID customerId);
}
