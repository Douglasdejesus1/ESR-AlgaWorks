package com.douglas.algafood.api.model.mixin;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.PositiveOrZero;

import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Endereco;
import com.douglas.algafood.domain.model.FormaPagamento;
import com.douglas.algafood.domain.model.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RestauranteMixin {

	@PositiveOrZero	private BigDecimal taxaFrete;
	
	
	@JsonIgnoreProperties(value= "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	//@JsonIgnore
	private OffsetDateTime dataCadastro;	
	
	//@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private List<Produto>produtos = new ArrayList<>();
}
