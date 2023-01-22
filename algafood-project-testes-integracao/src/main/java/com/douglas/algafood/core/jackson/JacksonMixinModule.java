package com.douglas.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.douglas.algafood.api.model.mixin.RestauranteMixin;
import com.douglas.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

import groovyjarjarpicocli.CommandLine.Command;
@Component
public class JacksonMixinModule extends SimpleModule {
	private static final long serialVersionUID = 1L;
	
	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
	}

}
