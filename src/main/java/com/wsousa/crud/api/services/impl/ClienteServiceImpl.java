package com.wsousa.crud.api.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsousa.crud.api.domain.Cliente;
import com.wsousa.crud.api.repositories.ClienteRepository;
import com.wsousa.crud.api.repositories.ClienteRepositoryCustom;
import com.wsousa.crud.api.services.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService, ClienteRepositoryCustom {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ClienteRepository clienteRespository;

	@Override
	public List<Cliente> listarTodos() {
		   LOG.debug("This is a debug message");
		   LOG.info("This is an info message");
		   LOG.warn("This is a warn message");
		   LOG.error("This is an error message");
		return this.clienteRespository.findAll();
	}

	@Override
	public Cliente listarPorId(Long id) {
		return this.clienteRespository.findOne(id);
	}

	@Override
	public Cliente cadastrar(Cliente cliente) {
		return this.clienteRespository.save(cliente);
	}

	@Override
	public Cliente atualizar(Cliente cliente) {
		return this.clienteRespository.save(cliente);
	}

	@Override
	public void remover(Long id) {
		this.clienteRespository.delete(id);
	}

	@Override
	public List<Cliente> findByIDAsc() {
		LOG.info("buscar todos findByIDAsc");
		return this.clienteRespository.findByIDAsc();
	}

	@Override
	public List<Cliente> findByPredicate() {
		return null;
	}

	@Override
	public List<Cliente> listarPorManyToOne() {
		return clienteRespository.listarPorManyToOne();
	}

	@Override
	public List<Cliente> listClientePorManyToOneNativeQuery() {
		LOG.info("EXECUTE NATIVE QUERY");
		return clienteRespository.listClientePorManyToOneNativeQuery();
	}
	
	
	@Override
	public List<Cliente> listClientePorManyToOneNativeQuery(Long id) {
		LOG.info("EXECUTE NATIVE QUERY FOR ID"); 
		return clienteRespository.listClientePorManyToOneNativeQueryForId(id);
	}
	
	@Override
	public List<Cliente> listClientePorManyToOneNativeQueryForIdParam(Long id) {
		LOG.info("EXECUTE NATIVE QUERY FOR ID FOR PARAM"); 
		return clienteRespository.listClientePorManyToOneNativeQueryForIdParam(id);
	}

	@Override
	public Optional<Cliente> readByClientName(String nome) {
		LOG.info("EXECUTE NATIVE QUERY FOR ID FOR PARAM " +nome.toUpperCase()); 
		return clienteRespository.readByClientName(nome);
	}
	

}
