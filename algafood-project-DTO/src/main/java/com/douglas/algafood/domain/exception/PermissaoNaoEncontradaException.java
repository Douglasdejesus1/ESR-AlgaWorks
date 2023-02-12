package com.douglas.algafood.domain.exception;

public class PermissaoNaoEncontradaException  extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public PermissaoNaoEncontradaException(String msg) {
		super(msg);
		
	}
	
	public PermissaoNaoEncontradaException(Long permissaoId) {
		this(String.format("Permissao com Id %d não existe no banco de dados", permissaoId));
		
	}
	
	

}
