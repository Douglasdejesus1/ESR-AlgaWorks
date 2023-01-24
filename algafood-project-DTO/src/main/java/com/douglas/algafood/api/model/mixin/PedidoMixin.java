package com.douglas.algafood.api.model.mixin;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoMixin {

	@JsonIgnore
	private OffsetDateTime dataCriacao;

}
