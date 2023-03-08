package com.douglas.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.FotoProdutoModel;
import com.douglas.algafood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelAssembler {

	@Autowired
	ModelMapper modelMapper;
	
	public FotoProdutoModel toModel(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoModel.class);
	}
	
	/*public List<FotoProdutoModel> toCollectionModel(List<FotoProduto>fotosProduto){
		return fotosProduto.stream()
				.map(foto -> toModel(foto))
				.collect(Collectors.toList());
	}*/
}
