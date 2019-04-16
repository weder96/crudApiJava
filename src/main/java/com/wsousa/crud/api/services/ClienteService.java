package com.wsousa.crud.api.services;

import java.util.List;
import java.util.Optional;

import com.wsousa.crud.api.domain.Cliente;

public interface ClienteService {
	
	List<Cliente> listarTodos();
	
	List<Cliente> findByIDAsc();
	
	List<Cliente> findByPredicate();
	
	Cliente listarPorId(Long id);
	
	Cliente cadastrar(Cliente cliente);
	
	Cliente atualizar(Cliente cliente);
	
	void remover(Long id);
	
	List<Cliente> listarPorManyToOne();
	
	List<Cliente> listClientePorManyToOneNativeQuery();
	
	List<Cliente> listClientePorManyToOneNativeQuery(Long id);
	
	List<Cliente> listClientePorManyToOneNativeQueryForIdParam(Long id);
	
	Optional<Cliente> readByClientName(String nome);
	

}
