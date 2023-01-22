package com.douglas.algafood.api.model.mixin;

import java.util.ArrayList;
import java.util.List;

import com.douglas.algafood.domain.model.Permissao;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class GrupoMixin {
	
	@JsonIgnore
	private List<Permissao>permissoes = new ArrayList<>();

}
