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

import com.douglas.algafood.api.assembler.CidadeInputDisassembler;
import com.douglas.algafood.api.assembler.CidadeModelAssembler;
import com.douglas.algafood.api.model.CidadeModel;
import com.douglas.algafood.api.model.input.CidadeInput;
import com.douglas.algafood.domain.exception.EstadoNaoEncontradaException;
import com.douglas.algafood.domain.exception.NegocioException;
import com.douglas.algafood.domain.model.Cidade;
import com.douglas.algafood.domain.repository.CidadeRepository;
import com.douglas.algafood.domain.service.CadastroCidadeService;
@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidade;
	
	@Autowired
	private CidadeModelAssembler cidadeModelAssembler;
	@Autowired
	private CidadeInputDisassembler cidadeInputDisassembler;
	
	@GetMapping
	public List<CidadeModel> listar(){
		return cidadeModelAssembler.toCollectionModel(cidadeRepository.findAll());
	}
	
	@GetMapping("/{cidadeId}")
	public CidadeModel buscar(@PathVariable("cidadeId") Long cidadeId){
		
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		return cidadeModelAssembler.toModel(cidade);
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInput cidadeInput) {
		try {
			Cidade cidade = cidadeInputDisassembler.toDomainObjec(cidadeInput);
			return cidadeModelAssembler.toModel(cadastroCidade.salvar(cidade));
			//return cidade = cadastroCidade.salvar(cidade);
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable  Long cidadeId, @Valid
			@RequestBody CidadeInput cidadeInput) {
		
		Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);
		
		cidadeInputDisassembler.copyToDomainObject(cidadeInput, cidadeAtual);
		try {
		return  cidadeModelAssembler.toModel(cadastroCidade.salvar(cidadeAtual));
		
		}catch(EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{cidadeId}")
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}
	

}

