package com.douglas.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

	private static final long serialVersionUID = 1L;

	public PedidoNaoEncontradoException(String msg) {
		super(msg);
	}

	public PedidoNaoEncontradoException(Long pedidoId) {
		this(String.format("Pedido com Id %d não existe no banco de dados", pedidoId));
	}
}
