package com.douglas.algafood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.douglas.algafood.domain.service.FotoStorageService;
@Service
public abstract class LocalFotoStorageService implements FotoStorageService{
	@Value("${algafood-project-DTO.storage.local.diretorio-fotos}")
	private Path diretorioFotos;
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());
			
				FileCopyUtils.copy(novaFoto.getInputStream(), 
						Files.newOutputStream(arquivoPath));
			} catch (IOException e) {
				throw new StorageException("Não foi possivel armazenar arquivo", e);
			}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return diretorioFotos.resolve(Path.of(nomeArquivo));
	}
	
	
}
