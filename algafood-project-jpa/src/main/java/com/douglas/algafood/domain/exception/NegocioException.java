package com.douglas.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST/*, reason = "Entidade não encontrada"*/)
public class NegocioException extends RuntimeException {
//public class EntidadeNaoEncontradaException extends ResponseStatusException {
	private static final long serialVersionUID = 1L;
	
	
	public NegocioException(String msg) {
		super(msg);
	}
}
