package com.douglas.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.algafood.api.assembler.PedidoModelAssembler;
import com.douglas.algafood.api.assembler.PedidoResumoModelAssembler;
import com.douglas.algafood.api.model.PedidoModel;
import com.douglas.algafood.api.model.PedidoResumoModel;
import com.douglas.algafood.domain.model.Pedido;
import com.douglas.algafood.domain.repository.PedidoRepository;
import com.douglas.algafood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelAssembler pedidoModelAssembler;
	@Autowired
	private PedidoResumoModelAssembler pedidoResumoModelAssembler;
	
	@GetMapping
	public List<PedidoResumoModel> listar(){
		return pedidoResumoModelAssembler.toCollectionModel(pedidoRepository.findAll());
	}
    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
        
        return pedidoModelAssembler.toModel(pedido);
    } 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
