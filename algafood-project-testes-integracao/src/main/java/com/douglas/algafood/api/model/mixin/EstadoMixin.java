package com.douglas.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.douglas.algafood.domain.model.Cidade;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class EstadoMixin {
	
	@JsonIgnore
	private List<Cidade>cidades = new ArrayList<>();

}
