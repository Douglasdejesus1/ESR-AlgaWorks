package com.douglas.algafood.domain.exception;

public class FormaPagamentoNaoEncontradaException  extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;

	public FormaPagamentoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public FormaPagamentoNaoEncontradaException(Long formaPagamentoId) {
		this(String.format("Forma Pagamento com Id %d não existe no banco de dados",formaPagamentoId));
	}
}
