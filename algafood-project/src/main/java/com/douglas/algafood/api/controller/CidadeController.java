package com.douglas.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeRepository.listar();
	}
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId") Long cidadeId){
		Cidade cidade = cidadeRepository.buscar(cidadeId);
		if(cidade !=null) {
			return ResponseEntity.status(HttpStatus.OK).body(cidade);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {
			cidade = cadastroCidade.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade){
		try {
			Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
			
			if(cidadeAtual != null) {
				BeanUtils.copyProperties(cidade, cidadeAtual, "id");
				cadastroCidade.salvar(cidadeAtual);
				return ResponseEntity.ok(cidadeAtual);
			}
			return ResponseEntity.notFound().build();
			}catch(EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId){
		try {
			cadastroCidade.excluir(cidadeId);
			return ResponseEntity.noContent().build();
		}catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

