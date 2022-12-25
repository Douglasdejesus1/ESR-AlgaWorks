package com.douglas.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.EntidadeEmUsoException;
import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.model.Estado;
import com.douglas.algafood.domain.repository.CidadeRepository;
import com.douglas.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {

		return estadoRepository.salvar(estado);
	}

	public void excluir(Long estadoId) {
		try {
			estadoRepository.remover(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("ID %d esta vazio", estadoId));

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("ID %d está em uso em outra entidade", estadoId));
		}

	}

}
