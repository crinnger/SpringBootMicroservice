package br.com.crinnger.SpringBootMicroservice.web.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.crinnger.SpringBootMicroservice.services.CustomerService;
import br.com.crinnger.SpringBootMicroservice.web.model.CustomerDto;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping({"/{customerID}"})
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerID") UUID customerId){
		return new ResponseEntity<>(this.customerService.getCustomerById(customerId),HttpStatus.OK); 
	}

}
