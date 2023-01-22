package com.douglas.algafood.api.model.mixin;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PedidoMixin {

	@JsonIgnore
	private LocalDateTime dataCriacao;

}
