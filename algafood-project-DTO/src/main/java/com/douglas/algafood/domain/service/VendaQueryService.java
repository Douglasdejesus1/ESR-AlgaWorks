package com.douglas.algafood.domain.service;

import java.util.List;

import com.douglas.algafood.domain.filter.VendaDiariaFilter;
import com.douglas.algafood.domain.model.dto.VendaDiaria;

public interface VendaQueryService {
	
	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro);

}
