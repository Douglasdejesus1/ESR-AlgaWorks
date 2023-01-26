package com.douglas.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.EstadoModel;
import com.douglas.algafood.api.model.input.CozinhaInput;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Estado;

@Component
public class CozinhaInputDissassembler {
	@Autowired
	ModelMapper modelMapper;
	
	public Cozinha toDomainObjec(CozinhaInput cozinhaInput) {
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(@Valid CozinhaInput cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
}
