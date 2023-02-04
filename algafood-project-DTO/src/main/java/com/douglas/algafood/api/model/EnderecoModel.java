package com.douglas.algafood.api.model;

import com.douglas.algafood.domain.model.Cidade;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class EnderecoModel {
	
	private String cep;
	
	private String logradouro;	
	
	private String numero;	
	
	private String complemento;
	
	private String bairro;
	
	private CidadeResumoModel cidade;
}
