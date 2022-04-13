package com.AppOficina.AppOficina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.AppOficina.AppOficina.models.Fornecedor;

public interface FornecedorRepository extends CrudRepository<Fornecedor, Long> {
	
	Fornecedor findById(long id);
	Fornecedor findByNome(String nome);
	
	//Query para busca
	@Query(value = "select u from Fornecedor u where u.nome like %?1%")
	List<Fornecedor>findByNomes(String nome);

}
