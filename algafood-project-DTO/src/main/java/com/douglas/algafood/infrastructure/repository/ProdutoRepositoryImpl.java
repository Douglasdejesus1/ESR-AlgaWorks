package com.douglas.algafood.infrastructure.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.algafood.domain.model.FotoProduto;
import com.douglas.algafood.domain.repository.ProdutoRepositoryQueries;

@Repository
public class ProdutoRepositoryImpl implements ProdutoRepositoryQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Transactional
	@Override
	public FotoProduto save(FotoProduto foto) {
		
		return manager.merge(foto);
	}
	
	@Transactional
	@Override
	public void delete(FotoProduto foto) {
		manager.remove(foto);
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
