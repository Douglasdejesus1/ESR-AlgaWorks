package com.douglas.algafood.domain.service;

import java.io.InputStream;

import groovy.transform.builder.Builder;
import lombok.Getter;

public interface FotoStorageService {

	void armazenar(NovaFoto novaFoto);

	@Builder
	@Getter
	class NovaFoto {
		private String nomeArquivo;
		private InputStream inputStream;

	}
}