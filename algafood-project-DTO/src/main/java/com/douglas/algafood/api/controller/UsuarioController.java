package com.douglas.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.algafood.api.assembler.UsuarioInputDisassembler;
import com.douglas.algafood.api.assembler.UsuarioModelAssembler;
import com.douglas.algafood.api.model.UsuarioModel;
import com.douglas.algafood.api.model.input.SenhaInput;
import com.douglas.algafood.api.model.input.UsuarioComSenhaInput;
import com.douglas.algafood.api.model.input.UsuarioInput;
import com.douglas.algafood.domain.model.Usuario;
import com.douglas.algafood.domain.repository.UsuarioRepository;
import com.douglas.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuario;
	
	@GetMapping
	public List<UsuarioModel> listar(){
		return usuarioModelAssembler.toCollectionModel(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioModel buscar(@PathVariable("usuarioId") Long usuarioId) {
		Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
		return usuarioModelAssembler.toModel(usuario);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput  usuarioInput) {
		Usuario usuario = usuarioInputDisassembler.toDomainObject(usuarioInput);
		return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @Valid
			@RequestBody UsuarioInput usuarioInput) {
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioInput, usuarioAtual);
		return usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuarioAtual));
	}
	
	/*@PutMapping("/{usuarioId}/senha")
	public void atualizarSenha(@PathVariable Long usuarioId, @Valid
			@RequestBody UsuarioSenhaInput usuarioSenhaInput) {
		Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
		usuarioInputDisassembler.copyToDomainObject(usuarioSenhaInput, usuarioAtual);
		usuarioModelAssembler.toModel(cadastroUsuario.salvar(usuarioAtual));
	}*/
	
	 @PutMapping("/{usuarioId}/senha")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
		 cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
	 }
	
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{usuarioId}")
		public void remover(@PathVariable Long usuarioId) {
			cadastroUsuario.excluir(usuarioId);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
