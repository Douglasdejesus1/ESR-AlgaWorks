package com.douglas.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douglas.algafood.domain.model.FotoProduto;
import com.douglas.algafood.domain.repository.ProdutoRepository;

@Service
public class CatalagoFotoProdutoService {
	
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto){
		
		return produtoRepository.save(foto);
	}
}

