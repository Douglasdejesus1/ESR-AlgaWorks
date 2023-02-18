package com.douglas.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;



	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format("Pedido com codigo %d não existe no banco de dados", codigoPedido));
	}
}
