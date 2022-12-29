package com.douglas.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	
	@PersistenceContext
	private EntityManager manager;
	
	/*@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal){
		//var type inference
		var jpql = "from Restaurante where nome like :nome "
				+"and taxaFrete between :taxaInicial and :taxaFinal";
		return manager.createQuery(jpql, Restaurante.class)
				.setParameter("nome", "%"+nome+"%")
				.setParameter("taxaInicial", taxaFreteInicial)
				.setParameter("taxaFinal", taxaFreteFinal)
				.getResultList();
	}*/
	
	//consulta dinamica JPQL
	/*
	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal) {

		var jpql = new StringBuilder();

		jpql.append("from Restaurante where 0 = 0 ");

		var parametros = new HashMap<String, Object>();

		if (StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		if (taxaFreteInicial != null) {
			jpql.append("and taxaFrete >= :taxaInicial ");
			parametros.put("taxaInicial", taxaFreteInicial);
		}
		if (taxaFreteFinal != null) {
			jpql.append("and taxaFrete <= :taxaFinal ");
			parametros.put("taxaFinal", taxaFreteFinal);
		}
		
		// TypedQuery é uma consulta tipada
		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), 
				Restaurante.class);
		parametros.forEach((chave,valor)->query.setParameter(chave, valor));
		
		return query.getResultList();
	}*/
	
	//5.14 - Consulta com CRITERIA API ESTATIVO
	/*public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, 
			BigDecimal taxaFreteFinal) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		//consulta simples
		//criteria.from(Restaurante.class);
		
		Root<Restaurante> root = criteria.from(Restaurante.class);
		Predicate nomePredicate = builder.like(root.get("nome"), "%"+nome+"%");
		
		Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial);
		Predicate taxaFinalPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal);
		
		criteria.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}*/
	//5.15 - Consulta com CRITERIA API DINAMICO
		public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, 
				BigDecimal taxaFreteFinal) {
			
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			
			var predicates  = new ArrayList<Predicate>();
			
			CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
			
			
			Root<Restaurante> root = criteria.from(Restaurante.class);
			
			if(StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%"+nome+"%"));
			}
			if(taxaFreteInicial !=null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
			}
			if(taxaFreteFinal !=null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
			}
			
			criteria.where(predicates.toArray(new Predicate[0]));
			
			TypedQuery<Restaurante> query = manager.createQuery(criteria);
			return query.getResultList();
		}

}