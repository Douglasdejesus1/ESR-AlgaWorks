package com.douglas.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.input.UsuarioInput;
import com.douglas.algafood.domain.model.Usuario;

@Component
public class UsuarioInputDisassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Usuario toDomainObject(UsuarioInput usuarioInput) {
		return modelMapper.map(usuarioInput, Usuario.class);
	}
	
	public void copyToDomainObject(UsuarioInput usuarioInput, Usuario grupo) {
		modelMapper.map(usuarioInput, grupo);
	}

}
