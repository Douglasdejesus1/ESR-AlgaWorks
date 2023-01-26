package com.douglas.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.douglas.algafood.api.assembler.EstadoInputDisassembler;
import com.douglas.algafood.api.assembler.EstadoModelAssembler;
import com.douglas.algafood.api.model.EstadoModel;
import com.douglas.algafood.domain.model.Estado;
import com.douglas.algafood.domain.repository.EstadoRepository;
import com.douglas.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@GetMapping
	public List<EstadoModel> listar(){
		return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
	}
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable("estadoId") Long estadoId){
		Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
		return  estadoModelAssembler.toModel(estado);
		
	}
	
	//POST
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public EstadoModel adicionar(@RequestBody @Valid EstadoModel estadoInput) {
		Estado estado = estadoInputDisassembler.toDomainObjec(estadoInput);
		return estadoModelAssembler.toModel(cadastroEstado.salvar(estado));
	}
	
	//PUT
	@PutMapping("/{estadoId}")
	public EstadoModel atualizar(@PathVariable Long estadoId, @Valid @RequestBody EstadoModel estadoInput){
		
		Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
		
			
			return estadoModelAssembler.toModel(cadastroEstado.salvar(estadoAtual));
				}
	//DELETE
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{estadoId}")
	public void remover(@PathVariable Long estadoId) {
			cadastroEstado.excluir(estadoId);			
	}
}

