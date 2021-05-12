package br.com.crinnger.SpringBootMicroservice.test;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.crinnger.SpringBootMicroservice.controller.BeerController;
import br.com.crinnger.SpringBootMicroservice.domain.Beer;
import br.com.crinnger.SpringBootMicroservice.model.BeerDto;
import br.com.crinnger.SpringBootMicroservice.model.BeerStyleEnum;
import br.com.crinnger.SpringBootMicroservice.repositories.BeerRepository;
import br.com.crinnger.SpringBootMicroservice.services.BeerService;

import java.util.Optional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document; 
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(RestDocumentationExtension.class)
@RunWith(SpringRunner.class) 
@WebMvcTest(BeerController.class) 
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc; 
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;
	
    @MockBean
    BeerRepository beerRepository; 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test 
	void testGetBeer() throws Exception{
		 given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));
		this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk());
	} 

	@Test
	void testHandlePost() throws Exception {	
		
		String json=this.objectMapper.writeValueAsString(getValidBeer());
		
		ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
		
		this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/beer/")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(json))
				.andExpect(MockMvcResultMatchers.status().isCreated());	
	}

	@Test
	void testHandleUpdate()   throws Exception {
				
		String json=this.objectMapper.writeValueAsString(getValidBeer());
		
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

	BeerDto getValidBeer() {
		return BeerDto.builder()
				.beerName("bud")
				.beerStyle(BeerStyleEnum.ALE)
				.price(new BigDecimal("100"))
				.upc(4545L)
				.build(); 
	}
	
    private static class ConstrainedFields {

        private final ConstraintDescriptions constraintDescriptions;

        ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ". ")));
        }
    }
}
