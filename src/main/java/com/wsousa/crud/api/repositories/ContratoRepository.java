package com.wsousa.crud.api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.wsousa.crud.api.domain.Contrato;

@Transactional(readOnly = true)
public interface ContratoRepository extends JpaRepository<Contrato, Long> {	
	
	 @Transactional
	 @Query("SELECT c FROM Contrato c order by c.id desc")
	 public List<Contrato> findByIDAsc();
	
	 

}
