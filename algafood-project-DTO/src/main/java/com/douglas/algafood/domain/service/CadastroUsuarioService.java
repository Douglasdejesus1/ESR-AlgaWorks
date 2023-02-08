package com.douglas.algafood.domain.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.algafood.domain.exception.NegocioException;
import com.douglas.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.douglas.algafood.domain.model.Usuario;
import com.douglas.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		if(usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário");
		}
		usuario.setSenha(novaSenha);
	}
	
	
	
	
	@Transactional
	public void excluir(Long usuarioId) {
		try{
			usuarioRepository.deleteById(usuarioId);
		}catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);
		}
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId).orElseThrow(()->
		new UsuarioNaoEncontradoException(usuarioId));
	}
}
