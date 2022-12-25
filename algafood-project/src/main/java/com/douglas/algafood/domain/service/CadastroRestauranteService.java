package com.douglas.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.repository.CozinhaRepository;
import com.douglas.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired 
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	/*
	public Restaurante salvar(Restaurante restarante) {
		return restauranteRepository.salvar(restarante);
	}
	*/
	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.buscar(cozinhaId);
		
		if(cozinha == null) {
		 throw new EntidadeNaoEncontradaException(
				String.format("Cozinha com Id %d não existe no banco de dados", cozinhaId));
		}
		restaurante.setCozinha(cozinha);
		return restauranteRepository.salvar(restaurante);
	}

	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.remover(restauranteId);
			}catch(EmptyResultDataAccessException e) {
				throw new EntidadeNaoEncontradaException(String.format("ID %d esta vazio",restauranteId));
			}
	}

	
	
}
