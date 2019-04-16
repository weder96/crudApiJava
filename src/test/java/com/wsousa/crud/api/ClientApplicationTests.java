package com.wsousa.crud.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.gson.Gson;
import com.wsousa.crud.api.controllers.ClienteController;
import com.wsousa.crud.api.domain.Cliente;
import com.wsousa.crud.api.services.ClienteService;
import com.wsousa.crud.api.util.TestUtil;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ClienteController.class)
public class ClientApplicationTests {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	 
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ClienteService clienteService;
			 
	
	
	@Test
	public void validate04Delete() throws Exception {
		LOG.info("validateDelete");
		Cliente cli = new Cliente();
		cli.setId(Long.parseLong("2"));	
				  		    
	     mockMvc.perform(delete("/api/clientes/{id}", cli.getId()))
		            .andExpect(status().is2xxSuccessful())
		            .andDo(print());
	
	}
	
	@Test
	public void validate01Select() throws Exception {
		LOG.info("validateSelect");		
		
		MvcResult result = mockMvc.perform(get("/api/clientes"))				
        		.andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)) 
                .andDo(print())
                .andReturn();
		
			LOG.info("result: "+result.getResponse().getContentType());
			

				 				
	}
	
	
	@Test
	public void validate02Insert() throws Exception {
		LOG.info("validateInsert");
		Cliente cli = new Cliente();
		cli.setCpf("52988756619");
		cli.setNome("testeNomeNovo");
		cli.setEmail("teste@gmail.com");
	    
	    Gson gson = new Gson();
	    
	    mockMvc.perform(post("/api/clientes")
	    				.contentType(TestUtil.APPLICATION_JSON_UTF8)
	                    .content(gson.toJson(cli)))
	    				.andDo(print())
	            		.andExpect(status().is2xxSuccessful());
	    
		
	}
	
	@Test
	public void validate03Update() throws Exception {
		LOG.info("validateUpdate");
		Cliente cli = new Cliente();
		cli.setId(2L);
		cli.setCpf("950.641.091-72");
		cli.setNome("testeNomeNovo");
		cli.setEmail("teste@gmail.com");
		
		Gson gson = new Gson();
		MvcResult result2 = mockMvc.perform(put("/api/clientes/"+cli.getId())
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(gson.toJson(cli)))						
        		.andExpect(status().is2xxSuccessful())  
        		.andDo(print())
                .andReturn();
		
			LOG.info("result: "+result2.getResponse().getContentAsString());
		
	}


}
