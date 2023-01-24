package com.douglas.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.mixin.CidadeMixin;
import com.douglas.algafood.api.model.mixin.CozinhaMixin;
import com.douglas.algafood.api.model.mixin.EstadoMixin;
import com.douglas.algafood.api.model.mixin.GrupoMixin;
import com.douglas.algafood.api.model.mixin.PedidoMixin;
import com.douglas.algafood.api.model.mixin.RestauranteMixin;
import com.douglas.algafood.domain.model.Cidade;
import com.douglas.algafood.domain.model.Cozinha;
import com.douglas.algafood.domain.model.Estado;
import com.douglas.algafood.domain.model.Grupo;
import com.douglas.algafood.domain.model.Pedido;
import com.douglas.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;
@Component
public class JacksonMixinModule extends SimpleModule {
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
		setMixInAnnotation(Estado.class, EstadoMixin.class);
		setMixInAnnotation(Grupo.class, GrupoMixin.class);
		setMixInAnnotation(Pedido.class, PedidoMixin.class);
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}

}
