package com.wsousa.crud.api.controllers;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsousa.crud.api.domain.Contrato;
import com.wsousa.crud.api.responses.Response;
import com.wsousa.crud.api.services.ContratoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="contracts online", description="Operations pertaining to Contracts online")
@RestController
@RequestMapping(path = "/api/contratos")
public class ContratoController {
	
	@Autowired
	private ContratoService contratoService;
	
	
	@ApiOperation(value = "View a list of available Contrato Service", response = Iterable.class)
	@GetMapping
	public List<Contrato> listarTodos() {
		return this.contratoService.listarTodos();
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Contrato>> listarPorId(@PathVariable(name = "id") Long id) {
		return ResponseEntity.ok(new Response<Contrato>(this.contratoService.listarPorId(id)));
	}

	
	@PostMapping
	public ResponseEntity<Response<Contrato>> cadastrar(@Valid @RequestBody Contrato contrato, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Contrato>(erros));
		}
		
		return ResponseEntity.ok(new Response<Contrato>(this.contratoService.cadastrar(contrato)));
	}
	
	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Contrato>> atualizar(@PathVariable(name = "id") Long id, @Valid @RequestBody Contrato contrato, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Contrato>(erros));
		}
		
		contrato.setId(id);
		return ResponseEntity.ok(new Response<Contrato>(this.contratoService.atualizar(contrato)));
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name = "id") Long id) {
		this.contratoService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

}
