package com.wsousa.crud.api.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.wsousa.crud.api.domain.Cliente;
import com.wsousa.crud.api.responses.Response;
import com.wsousa.crud.api.services.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
@RestController
@RequestMapping(path = "/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	
	@ApiOperation(value = "View a list of available products", response = Iterable.class)
	@GetMapping
	public ResponseEntity<?>  listarTodos() throws URISyntaxException {
		List<Cliente> cliResources = this.clienteService.listarTodos(); 		
		return ResponseEntity.created(new URI("all")).body(cliResources);		
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Cliente>> listarPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.listarPorId(id)));
	}
	
	@GetMapping(path = "/all")
	public List<Cliente> listarPorIdAsc() {		
		return this.clienteService.findByIDAsc();
	}
	
	@GetMapping(path = "/cliente/contract")
	public List<Cliente> listarPorManyToOne() {
		return this.clienteService.listarPorManyToOne();
	}
	
	@GetMapping(path = "/cliente/native")
	public List<Cliente> listClientePorManyToOneNativeQuery() {
		return this.clienteService.listClientePorManyToOneNativeQuery();
	}
	
	@GetMapping(path = "/cliente/native/{id}")
	public List<Cliente> listClientePorManyToOneNativeQuery(@PathVariable(name = "id") Long id) {
		return this.clienteService.listClientePorManyToOneNativeQuery(id);
	}
	
	@GetMapping(path = "/cliente/nativeParam/{id}")
	public List<Cliente> listClientePorManyToOneNativeQueryForIdParam(@PathVariable(name = "id") Long id) {
		return this.clienteService.listClientePorManyToOneNativeQueryForIdParam(id);
	}
	
	@GetMapping(path = "/cliente/nativeParamNome/{nome}")
	public Optional<Cliente> listClientePorManyToOneNativeQueryForIdParam(@PathVariable(name = "nome") String nome) {
		return this.clienteService.readByClientName(nome);
	}
	
	
	@PostMapping
	public ResponseEntity<Response<Cliente>> cadastrar(@Valid @RequestBody Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			this.clienteService.cadastrar(cliente);
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.cadastrar(cliente)));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Cliente>> atualizar(@PathVariable(name = "id") Long id, @Valid @RequestBody Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
		    return ResponseEntity.badRequest().body(new Response<Cliente>(this.clienteService.atualizar(cliente)));
		}
		
		cliente.setId(id);
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.atualizar(cliente)));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name = "id") Long id) {
		this.clienteService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

}
