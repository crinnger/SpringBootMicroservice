package br.com.crinnger.SpringBootMicroservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.crinnger.SpringBootMicroservice.web.model.CustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public CustomerDto getCustomerById(UUID id) {
		// TODO Auto-generated method stub
		return CustomerDto.builder()
				.ID(id)
				.name("Crinnger")
				.build();
	}

	@Override
	public CustomerDto saveCustomer(CustomerDto customerDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto customerDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteByID(UUID customerId) {
		// TODO Auto-generated method stub
		
	}

}
