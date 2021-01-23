package br.com.crinnger.SpringBootMicroservice.test;

import java.util.UUID;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crinnger.SpringBootMicroservice.services.BeerService;
import br.com.crinnger.SpringBootMicroservice.web.controller.BeerController;
import br.com.crinnger.SpringBootMicroservice.web.model.BeerDto;

@RunWith(SpringRunner.class) 
@WebMvcTest(BeerController.class) 
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testGetBeer() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/" + UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	} 

	@Test
	void testHandlePost() throws Exception {
		BeerDto beer=BeerDto.builder().build();
		
		String json=this.objectMapper.writeValueAsString(beer);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}

	@Test
	void testHandleUpdate()   throws Exception {
		BeerDto beer=BeerDto.builder().build();
		
		String json=this.objectMapper.writeValueAsString(beer);
		
		this.mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/beer/" + UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	@Test
	void testDeleteBeer() throws Exception{
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/beer/" + UUID.randomUUID().toString()))
								.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

}
