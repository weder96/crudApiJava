package com.wsousa.crud.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.wsousa.crud.api.domain.Cliente;
import com.wsousa.crud.api.repositories.ClienteRepository;
import com.wsousa.crud.api.services.ClienteService;
import com.wsousa.crud.api.util.TestUtil;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.benas.randombeans.randomizers.range.BigDecimalRangeRandomizer;
import io.github.benas.randombeans.randomizers.range.IntegerRangeRandomizer;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import static io.restassured.RestAssured.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class ClientControllerTest {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	 @Autowired
	 WebApplicationContext webApplicationContext;
	 
	 @MockBean
	 ClienteService clienteService;
	 
	 @MockBean
	 ClienteRepository clienteRepository;
	 
	 
	 @LocalServerPort
	 private int port;
	 
	    private MockMvc mockMvc;
	    private ObjectMapper mapper = new ObjectMapper();
	    private ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	    
	    
	    @Before
	    public void setup() {
	    	   this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	           RestAssuredMockMvc.mockMvc(this.mockMvc);

	           // Object Map
	           this.mapper = new ObjectMapper();
	           this.objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
	           
	           EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
	                   .objectPoolSize(5)
	                   .randomize(Integer.class, new IntegerRangeRandomizer(0, 100))
	                   .randomize(BigDecimal.class, new BigDecimalRangeRandomizer(1l, 100l)) // l de LONG Ex. 1L
	                   .randomizationDepth(1)
	                   .stringLengthRange(5, 10)
	                   .collectionSizeRange(1, 2)
	                   .scanClasspathForConcreteTypes(true)
	                   .overrideDefaultInitialization(false)
	                   .build();

	    	
	    }
	    
	    @After
	    public void finish() {
	  
	    }
	    
	    @Test
	    public void findClientByExistent01One(){
	    	
	    	 //when(clienteService.listarTodos()).thenReturn(List<Cliente>);
	    	 
			    try {
			    	clienteService.listarTodos().forEach(item ->{
			    		LOG.info("find:"+item.getNome());
			    	});
			    	
					MvcResult result = mockMvc.perform(get("/api/clientes"))				
							.andExpect(status().is2xxSuccessful())
					        .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)) 
					        .andDo(print())
					        .andReturn();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    }
	    
	    
	    @Test
	    public void findClientByExistent02Two(){
	    	try {
	    		
	    	List<Cliente> lista = new ArrayList<>(Arrays.asList(given()
	    	            .when()
	    	           // .port(port)
	    	            .get("/api/clientes")
	    	            .then()
	    	            .extract()
	    	            .response()
	    	            .body()
	    	            .as(Cliente[].class)));
	    	
				    	lista.forEach(item->{
				    		LOG.info(""+item.getId());
				    		LOG.info(""+item.getNome());
				    	});
	    			    	
	    	} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	                
	    }
	    
	    
}	    