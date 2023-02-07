package com.douglas.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeEmUsoException{
	private static final long serialVersionUID = 1L;

	public GrupoNaoEncontradoException(String msg) {
		super(msg);
	}
	public GrupoNaoEncontradoException(Long grupoId) {
		this(String.format("Grupo com Id %d não existe no banco de dados", grupoId));
	}

}
