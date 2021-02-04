package br.com.crinnger.SpringBootMicroservice.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crinnger.SpringBootMicroservice.controller.CustomerController;
import br.com.crinnger.SpringBootMicroservice.model.CustomerDto;
import br.com.crinnger.SpringBootMicroservice.services.CustomerService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	CustomerService customerService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetCustomer() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testHandlePost() throws Exception{
		CustomerDto customer=CustomerDto.builder().build();
		
		String json=this.objectMapper.writeValueAsString(customer);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testExecutarPut() throws Exception{
		CustomerDto customer=CustomerDto.builder().build();
		
		String json=this.objectMapper.writeValueAsString(customer);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/"+ UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void testDeleteBeer() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer/"+ UUID.randomUUID().toString()))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
