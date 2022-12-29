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
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if(estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado com Id %d não existe no banco de dados", estadoId));
		}
		cidade.setEstado(estado.get());
		return cidadeRepository.save(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.format("ID %d está vazio", cidadeId));
			
		}
	}
}
