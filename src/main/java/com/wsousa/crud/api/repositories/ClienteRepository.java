package com.wsousa.crud.api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wsousa.crud.api.domain.Cliente;

@Transactional(readOnly = true)
public interface ClienteRepository extends JpaRepository<Cliente, Long>,ClienteRepositoryCustom{	
	
	 @Transactional
	 @Query("SELECT c FROM Cliente c order by c.id desc")
	 public List<Cliente> findByIDAsc();
	
	 @Query("SELECT cc FROM Contrato cc INNER JOIN cc.cliente cliente")
	 public List<Cliente> listarPorManyToOne();
	 
	
	 @Query(value="SELECT cli.* FROM Contrato cc INNER JOIN cliente cli on cli.id = cc.cliente_id", nativeQuery = true)
	 public List<Cliente> listClientePorManyToOneNativeQuery();
	 
	 
	 @Query(value="SELECT cli.* FROM Contrato cc INNER JOIN cliente cli on cli.id = cc.cliente_id and cli.id=?1", nativeQuery = true)
	 public List<Cliente> listClientePorManyToOneNativeQueryForId(Long id);
	 
	 
	 @Query(value="SELECT cli.* FROM Contrato cc INNER JOIN cliente cli on cli.id = cc.cliente_id and cli.id=:id", nativeQuery = true)
	 public List<Cliente> listClientePorManyToOneNativeQueryForIdParam(@Param("id") Long id);
	 
	 @Query(value="SELECT cli.* FROM Contrato cc INNER JOIN cliente cli on cli.id = cc.cliente_id and cli.nome=:nome", nativeQuery = true)
	 public Optional<Cliente> readByClientName(@Param("nome") String nome);

}
