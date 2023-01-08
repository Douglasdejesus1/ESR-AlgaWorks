package com.douglas.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","Entidade não econtrada");
	
	private String uri;
	private String title;
	
	private ProblemType(String path, String title) {
		
		this.uri = "hhtps:/algafood.com.br"+path;
		this.title = title;
	}

}
