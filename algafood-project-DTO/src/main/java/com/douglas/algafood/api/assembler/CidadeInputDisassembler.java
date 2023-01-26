package com.douglas.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.input.CidadeInput;
import com.douglas.algafood.domain.model.Cidade;
import com.douglas.algafood.domain.model.Estado;

@Component
public class CidadeInputDisassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Cidade toDomainObjec(CidadeInput cidadeInput) {
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	public void copyToDomainObject(CidadeInput cidadeInput, Cidade cidade) {
	//Para evidar o erro: Caused by: org.hibernate.HibernateException: identifier of an instance of 
	//com.douglas.algafood.domain.model.Estado was altered from 1 to 2
			cidade.setEstado(new Estado());
			modelMapper.map(cidadeInput,cidade);
	}
}
