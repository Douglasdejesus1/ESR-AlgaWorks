package com.douglas.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String msg) {
		super(msg);
	}
	public RestauranteNaoEncontradoException(Long empresaId) {
		this(String.format("Empresa com Id %d não existe no banco de dados",empresaId));
	}
}
