package com.douglas.algafood.api.assembler;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.EstadoModel;
import com.douglas.algafood.api.model.input.EstadoInput;
import com.douglas.algafood.domain.model.Estado;

@Component
public class EstadoInputDisassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Estado toDomainObjec(@Valid EstadoModel estadoInput) {
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void copyToDomainObject(@Valid EstadoModel estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
}
