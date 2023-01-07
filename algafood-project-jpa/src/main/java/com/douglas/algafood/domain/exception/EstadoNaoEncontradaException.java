package com.douglas.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public EstadoNaoEncontradaException(String msg) {
		super(msg);
	}
	public EstadoNaoEncontradaException(Long estadoId) {
		this(String.format("Estado com Id %d não existe no banco de dados",estadoId));
	}
}
