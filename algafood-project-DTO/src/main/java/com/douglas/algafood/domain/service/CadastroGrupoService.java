package com.douglas.algafood.domain.service;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.GrupoNaoEncontradoException;
import com.douglas.algafood.domain.model.Grupo;
import com.douglas.algafood.domain.model.Permissao;
import com.douglas.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	/*
	 * private static final String MSG_GRUPO_EM_USO =
	 * "Grupo de código %d não pode ser removida, pois está em uso";
	 */

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired CadastroPermissaoService cadastroPermissao;

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}

	@Transactional
	public void excluir(Long grupoId) {
		try {
			grupoRepository.deleteById(grupoId);
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		}
	}
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
		grupo.desassociarPermissao(permissao);
	}
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupo = buscarOuFalhar(grupoId);
		Permissao permissao = cadastroPermissao.buscarOuFalhar(permissaoId);
		grupo.adicionarPermissao(permissao);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	public Grupo buscarOuFalhar(Long grupoId) {
		return grupoRepository.findById(grupoId).orElseThrow(() -> new GrupoNaoEncontradoException(grupoId));
	}
}
