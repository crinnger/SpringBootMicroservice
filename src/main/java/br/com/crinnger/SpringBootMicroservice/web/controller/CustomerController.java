package br.com.crinnger.SpringBootMicroservice.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.crinnger.SpringBootMicroservice.services.CustomerService;
import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.web.model.CustomerDto;

@Validated
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {
	private final CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@GetMapping({"/{customerID}"})
	public ResponseEntity<CustomerDto> getCustomer(@NotNull @PathVariable("customerID") UUID customerId){
		return new ResponseEntity<>(this.customerService.getCustomerById(customerId),HttpStatus.OK); 
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> handlePost(@NotNull @Valid @RequestBody CustomerDto customerDto){
		CustomerDto saveCustomer=this.customerService.saveCustomer(customerDto); 
		HttpHeaders headers=new HttpHeaders();  
		headers.add("Location", "/api/v1/customer/" + saveCustomer.getId().toString()); 
		return new ResponseEntity<CustomerDto>(headers,HttpStatus.CREATED); 
	}
	
	@PutMapping({"/{customerID}"})
	public ResponseEntity<CustomerDto> executarPut(@NotNull  @PathVariable("customerID") UUID customerId,@Valid @RequestBody CustomerDto customerDto){
		this.customerService.updateCustomer(customerId,customerDto);
		return new ResponseEntity<CustomerDto> (HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping({"/{customerID}"}) 
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@NotNull  @PathVariable("customerID") UUID customerId) {
		this.customerService.deleteByID(customerId); 
	}
	


}
