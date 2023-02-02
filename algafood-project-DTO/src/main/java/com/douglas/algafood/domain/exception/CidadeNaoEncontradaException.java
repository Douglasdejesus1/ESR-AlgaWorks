package com.douglas.algafood.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Cidade com Id %d não existe no banco de dados",cidadeId));
	}
}
