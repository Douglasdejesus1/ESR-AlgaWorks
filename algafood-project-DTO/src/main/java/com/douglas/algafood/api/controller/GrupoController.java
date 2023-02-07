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

import com.douglas.algafood.api.assembler.GrupoInputDisassembler;
import com.douglas.algafood.api.assembler.GrupoModelAssembler;
import com.douglas.algafood.api.model.GrupoModel;
import com.douglas.algafood.api.model.input.GrupoInput;
import com.douglas.algafood.domain.model.Grupo;
import com.douglas.algafood.domain.repository.GrupoRepository;
import com.douglas.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	
	@GetMapping
	public List<GrupoModel> listar(){
		return grupoModelAssembler.toCollectionModel(grupoRepository.findAll());
	}
	
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable("grupoId") Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		return grupoModelAssembler.toModel(grupo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoInputDisassembler.toDomainObject(grupoInput);
		return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupo));
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId, @Valid
			@RequestBody GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupoAtual);
		return grupoModelAssembler.toModel(cadastroGrupo.salvar(grupoAtual));
	}
	
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{grupoId}")
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.excluir(grupoId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
