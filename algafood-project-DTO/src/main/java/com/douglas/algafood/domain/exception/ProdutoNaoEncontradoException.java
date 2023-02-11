package com.douglas.algafood.domain.exception;

public class ProdutoNaoEncontradoException  extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradoException(String msg) {
		super(msg);
		
	}
	
	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
		this(String.format("Produto com Id %d para o Restaurante com Id %d não existe no banco de dados", produtoId, restauranteId));
		
	}
	
	

}
