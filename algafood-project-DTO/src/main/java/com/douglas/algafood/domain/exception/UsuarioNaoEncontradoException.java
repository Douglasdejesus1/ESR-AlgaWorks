package com.douglas.algafood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeEmUsoException {
	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public UsuarioNaoEncontradoException(Long usuarioId) {
		this(String.format("Usuario com Id %d não existe no banco de dados", usuarioId));
	}
	
	

}
