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

}
