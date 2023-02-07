package com.douglas.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.CidadeNaoEncontradaException;
import com.douglas.algafood.domain.exception.GrupoNaoEncontradoException;
import com.douglas.algafood.domain.model.Cidade;
import com.douglas.algafood.domain.model.Grupo;
import com.douglas.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	/*private static final String MSG_GRUPO_EM_USO 
	= "Grupo de código %d não pode ser removida, pois está em uso";*/
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
		}catch(EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
	}
	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(()->
		new GrupoNaoEncontradoException(grupoId));
	}
}
