package com.wsousa.crud.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.wsousa.crud.api.domain.Cliente;
import com.wsousa.crud.api.services.ClienteService;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientApplicationRestTemplateTests {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	     
    @MockBean
    private ClienteService clienteService;
			 
    @LocalServerPort
    int port;
	
	
	@Test
	public void validate01ListAllClients(){
		LOG.info("validateAllClients");		
		
	try {	
			RestTemplate restTemplate = new RestTemplate();
			String fooResourceUrl = "http://localhost:"+port+"/api/clientes/all";
			ResponseEntity<String> response = restTemplate.getForEntity(fooResourceUrl, String.class);
			LOG.info(response.getBody());
			assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		}catch (Exception e) {
			LOG.info(e.getMessage());	
		}
	}
	
	@Test
	public void validate02InsertClient() {
		try {
			
			LOG.info("validate02InsertClient");
			Cliente cli = new Cliente();
			
			cli.setCpf("950.641.091-72");
			cli.setNome("testeNomeNovo");
			cli.setEmail("teste@gmail.com");	
			
			Gson gson = new Gson();
			
			RestTemplate restTemplate = new RestTemplate();
			String fooResourceUrl = "http://localhost:"+port+"/api/clientes";
			LOG.info("port:"+port);
			HttpEntity<Cliente> request = new HttpEntity<Cliente>(cli);
			Cliente cli2 = restTemplate.postForObject(fooResourceUrl, request, Cliente.class);
			LOG.info(gson.toJson(cli2,Cliente.class));
		
		}catch (Exception e) {
			LOG.info(e.getMessage());	
		}
	}
	
	
	@Test
	public void validate03UpdateClient() {
		LOG.info("validate03UpdateClient");
		
			try {
				Cliente cli = new Cliente();
				cli.setId(5L);
				cli.setCpf("950.641.091-72");
				cli.setNome("testeNomeNovo");
				cli.setEmail("teste@gmail.com");	
				
				Gson gson = new Gson();
				
				RestTemplate restTemplate = new RestTemplate();
				String fooResourceUrl = "http://localhost:"+port+"/api/clientes/"+cli.getId();
				LOG.info("port:"+port);
				HttpEntity<Cliente> request = new HttpEntity<Cliente>(cli);
				restTemplate.put(fooResourceUrl, request);
			}catch (Exception e) {
				LOG.info(e.getMessage());	
			}
				
	}
	
	@Test
	public void validate04DeleteClient() {
		try {
			LOG.info("validate04DeleteClient");
			Cliente cli = new Cliente();
			cli.setId(5L);
									
			RestTemplate restTemplate = new RestTemplate();
			String fooResourceUrl = "http://localhost:"+port+"/api/clientes/"+cli.getId();
			LOG.info("port:"+port);			
			restTemplate.delete(fooResourceUrl);
		}catch (Exception e) {
			LOG.info(e.getMessage());	
		}
				
	}
	

}
