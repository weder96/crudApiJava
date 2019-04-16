package com.wsousa.crud.api.services.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wsousa.crud.api.domain.Contrato;
import com.wsousa.crud.api.repositories.ContratoRepository;
import com.wsousa.crud.api.services.ContratoService;

@Service
public class ContratoServiceImpl implements ContratoService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ContratoRepository contratoRespository;

	@Override
	public List<Contrato> listarTodos() {
		   LOG.debug("This is a debug message");
		   LOG.info("This is an info message");
		   LOG.warn("This is a warn message");
		   LOG.error("This is an error message");
		return this.contratoRespository.findAll();
	}

	@Override
	public Contrato listarPorId(Long id) {
		return this.contratoRespository.findOne(id);
	}

	@Override
	public Contrato cadastrar(Contrato Contrato) {
		return this.contratoRespository.save(Contrato);
	}

	@Override
	public Contrato atualizar(Contrato Contrato) {
		return this.contratoRespository.save(Contrato);
	}

	@Override
	public void remover(Long id) {
		this.contratoRespository.delete(id);
	}
	

}
