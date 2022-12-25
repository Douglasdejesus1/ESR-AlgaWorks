package com.douglas.algafood.domain.service;

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
		Estado estado = estadoRepository.buscar(estadoId);
		
		if(estado ==null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Estado com Id %d não existe no banco de dados", estadoId));
		}
		cidade.setEstado(estado);
		return cidadeRepository.salvar(cidade);
	}

	public void excluir(Long cidadeId) {
		try {
			cidadeRepository.remover(cidadeId);
		}catch(EmptyResultDataAccessException e){
			throw new EntidadeNaoEncontradaException(String.format("ID %d está vazio", cidadeId));
			
		}
	}
}
