package com.douglas.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.algafood.api.assembler.UsuarioModelAssembler;
import com.douglas.algafood.api.model.UsuarioModel;
import com.douglas.algafood.domain.model.Restaurante;
import com.douglas.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {
	
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	
	@GetMapping
	public List<UsuarioModel> listar(@PathVariable Long restauranteId){
		Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId); 
		return usuarioModelAssembler.toCollectionModel(restaurante.getUsuarios());
	}
	
	@DeleteMapping("/{usuarioId}")
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long  usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
	}
	@PutMapping("/{usuarioId}")
	public void  associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
