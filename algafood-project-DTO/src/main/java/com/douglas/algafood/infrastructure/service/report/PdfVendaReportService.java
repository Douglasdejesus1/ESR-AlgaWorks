package com.douglas.algafood.infrastructure.service.report;

import org.springframework.stereotype.Service;

import com.douglas.algafood.domain.filter.VendaDiariaFilter;
import com.douglas.algafood.domain.service.VendaReportService;

@Service
public class PdfVendaReportService implements VendaReportService {

	@Override
	public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
