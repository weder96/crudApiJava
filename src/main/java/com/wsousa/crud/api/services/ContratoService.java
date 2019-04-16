package com.wsousa.crud.api.services;

import java.util.List;
import com.wsousa.crud.api.domain.Contrato;

public interface ContratoService {
	
	List<Contrato> listarTodos();
	
	Contrato listarPorId(Long id);
	
	Contrato cadastrar(Contrato Contrato);
	
	Contrato atualizar(Contrato Contrato);
	
	void remover(Long id);
	

}
