package com.wsousa.crud.api.repositories;

import java.util.Optional;

import com.wsousa.crud.api.domain.Cliente;

public interface ClienteRepositoryCustom {
	public Optional<Cliente> readByClientName(String nome);

}
