package com.douglas.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não econtrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"); 
	
	private String uri;
	private String title;
	
	private ProblemType(String path, String title) {
		
		this.uri = "hhtps:/algafood.com.br"+path;
		this.title = title;
	}

}
