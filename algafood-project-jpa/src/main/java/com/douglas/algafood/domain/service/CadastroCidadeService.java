package com.douglas.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.model.Cidade;
import com.douglas.algafood.domain.model.Estado;
import com.douglas.algafood.domain.repository.CidadeRepository;
import com.douglas.algafood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	private static final String MSG_ESTADO_NAO_ESCONTRADO = "Estado com Id %d não existe no banco de dados";

	private static final String MSG_CIDADE_NAO_ENCONTRADA = "Não existe um cadastro de cidade com código %d";

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_ESTADO_NAO_ESCONTRADO, estadoId));
		}
		cidade.setEstado(estado.get());
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
			
		}
	}
	public Cidade buscarOuFalar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(()->
		new EntidadeNaoEncontradaException(String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
}
