package com.douglas.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.EntidadeEmUsoException;
import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.remover(cozinhaId);
		} catch(EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("ID %d esta vazio", cozinhaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("ID %d está em uso em outra entidade", cozinhaId));
		}
	}
}
