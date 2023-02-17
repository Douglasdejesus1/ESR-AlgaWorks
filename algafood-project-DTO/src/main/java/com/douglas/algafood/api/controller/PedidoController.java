package com.douglas.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.douglas.algafood.api.assembler.PedidoInputDisassembler;
import com.douglas.algafood.api.assembler.PedidoModelAssembler;
import com.douglas.algafood.api.assembler.PedidoResumoModelAssembler;
import com.douglas.algafood.api.model.PedidoModel;
import com.douglas.algafood.api.model.PedidoResumoModel;
import com.douglas.algafood.api.model.input.PedidoInput;
import com.douglas.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.douglas.algafood.domain.exception.NegocioException;
import com.douglas.algafood.domain.model.Pedido;
import com.douglas.algafood.domain.model.Usuario;
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
	private PedidoInputDisassembler pedidoInputDisassembler;
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
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
        try {
            Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);

            // TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedido.emitir(novoPedido);

            return pedidoModelAssembler.toModel(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
