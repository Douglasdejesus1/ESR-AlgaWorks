package com.douglas.algafood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.douglas.algafood.domain.model.Produto;
import com.douglas.algafood.domain.repository.ProdutoRepository;
import com.douglas.algafood.domain.repository.RestauranteRepository;
@Service
public class CadastroProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired 
	private RestauranteRepository restauranteRepository;
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId).orElseThrow(() 
				-> new ProdutoNaoEncontradoException(restauranteId, produtoId));
	}
	

}
