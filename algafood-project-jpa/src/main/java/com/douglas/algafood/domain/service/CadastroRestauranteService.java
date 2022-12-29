package com.douglas.algafood.domain.service;

import java.util.Optional;

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
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(()-> new EntidadeNaoEncontradaException(
				String.format("Cozinha com Id %d não existe no banco de dados", cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	/*public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
		if(cozinha.isEmpty()) {
		 throw new EntidadeNaoEncontradaException(
				String.format("Cozinha com Id %d não existe no banco de dados", cozinhaId));
		}
		restaurante.setCozinha(cozinha.get());
		return restauranteRepository.salvar(restaurante);
	}*/

	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
			}catch(EmptyResultDataAccessException e) {
				throw new EntidadeNaoEncontradaException(String.format("ID %d esta vazio",restauranteId));
			}
	}

	
	
}
