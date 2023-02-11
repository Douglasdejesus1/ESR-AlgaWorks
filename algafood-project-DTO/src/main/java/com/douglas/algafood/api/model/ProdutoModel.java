package com.douglas.algafood.api.model;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel {
	
	private Long id;
	private String nome;
	private String descricao;	
	private BigDecimal preco;	
	private Boolean ativo;	
	private String nomeRestaurante;

}
